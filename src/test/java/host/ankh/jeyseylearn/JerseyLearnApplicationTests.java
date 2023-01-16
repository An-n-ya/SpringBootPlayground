package host.ankh.jeyseylearn;

import host.ankh.jeyseylearn.domain.account.Account;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.MediaType;
import org.glassfish.jersey.client.ClientProperties;
import org.junit.jupiter.api.Test;
import org.slf4j.Marker;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Supplier;

class JerseyLearnApplicationTests extends JAXRSResourceBase {


	@Test
	void helloWhenUnauthenticated() throws Exception {
		var res = get("/hello");
		assertRedirect(res);
	}

	@Test
	void helloWhenAuthenticated() throws Exception {
		authenticatedScope(() -> {
			var res = get("/hello");
			log.debug(String.valueOf(res.getStatus()));
			for (Link link : res.getLinks()) {
				log.debug(link.toString());
			}
			res.getStringHeaders().forEach((a, b) -> {
				log.debug(a + "   "  + b.toString());

			});
			assertOK(res);
			var body = res.readEntity(String.class);
			assert body.equals("hello from spring");
		});
	}

	@Test
	void reverseWithParamsWhenAuth() throws Exception {
		authenticatedScope(() -> {
			var res = get("/reverse?data=hello");
			assertOK(res);
			assert res.readEntity(String.class).equals("olleh");
		});

	}

	@Test
	void createAccountWithAuth() throws Exception {
		authenticatedScope(() -> {
			var account = new Account("ankh", "ankh", "ankh", "17911112222", "ankh04@icloud.com");
			var res = post_json("/account/create", account);
			assertOK(res);
		});
	}

	@Test
	void createAccountWithoutAuth() throws Exception {
		var account = new Account("ankh", "ankh", "ankh", "17911112222", "ankh04@icloud.com");
		var res = post_json("/account/create", account);
		assertOK(res);
	}


	@Test
	void createAccountAndLoginWithThisAccount() throws Exception {
		var account = new Account("ankh", "ankh", "ankh", "17911112222", "ankh04@icloud.com");
		post_json("/account/create", account);
		Form form = new Form();
		form.param("username", "ankh");
		form.param("password", "ankh");
		Invocation.Builder builder = ClientBuilder.newClient()
				.target("http://localhost:" + port + "/login")
				.property(ClientProperties.FOLLOW_REDIRECTS, false)
				.request(MediaType.MULTIPART_FORM_DATA).accept(MediaType.TEXT_PLAIN);
		var res = builder.post(Entity.form(form));
		// 应该会重定位到主页
		assertRedirect(res);
		// 设置cookies
		setCookies(res.getCookies());
		// 访问需要权限的接口
		var res2 = get("/reverse?data=hello");
		assertOK(res2);
		assert res2.readEntity(String.class).equals("olleh");
	}

	@Test
	void createWrongAccount() throws Exception {
		// 创建错误的用户
		// 应该会出发ConstraintViolationException
		var account = new Account();
		// 故意不设置用户名
		account.setPassword("123");
		var res = post_json("/account/create", account);
		// 应该是badRequest
		assertBadRequest(res);
	}
}
