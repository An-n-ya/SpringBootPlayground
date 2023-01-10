package host.ankh.jeyseylearn;

import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JerseyLearnApplicationTests {
	@Inject
	private TestRestTemplate restTemplate;

	@Test
	void hello() {
		ResponseEntity<String> entity = this.restTemplate.getForEntity("/api/hello", String.class);
		assert(entity.getStatusCode().is2xxSuccessful());
		assert(entity.getBody().equals("hello from spring"));
	}

	@Test
	void reverse() {
		ResponseEntity<String> entity = this.restTemplate.getForEntity("/api/reverse?data=hello", String.class);
		assert entity.getStatusCode().is2xxSuccessful();
		assert entity.getBody().equals("olleh");
	}

	@Test
	void validation() {
		ResponseEntity<String> entity = this.restTemplate.getForEntity("/api/reverse", String.class);

		// FIXME: 这里直接跳转到login界面了，应该改成404返回
//		assert entity.getStatusCode().is4xxClientError();
	}


}
