package host.ankh.jeyseylearn;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

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
			assertOK(res);
			var body = res.readEntity(String.class);
			log.debug(new Supplier<String>() {
				@Override
				public String get() {
					return "\u001B[34m" + body + "\u001B[0m\n" ;
				}
			});
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
}
