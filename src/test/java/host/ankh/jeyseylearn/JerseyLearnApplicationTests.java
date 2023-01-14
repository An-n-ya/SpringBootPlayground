package host.ankh.jeyseylearn;

import jakarta.ws.rs.core.Link;
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
}
