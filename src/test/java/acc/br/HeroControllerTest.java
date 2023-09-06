package acc.br;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import acc.br.service.HeroService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
@TestHTTPEndpoint(HeroController.class)
public class HeroControllerTest {

	@Test
	public void testSize() {
		given()
				.when().get()
				.then()
					.statusCode(200);
	}

	@Test
	public void testBody() {
		given()
				.when().get()
				.then()
				.statusCode(200)
				.body(
						containsString("\"name\":\"Bruce\",\"surname\":\"Wayne\""),
						containsString("\"name\":\"Peter\",\"surname\":\"Parker\""));
	}

	@Test
	public void testPost() {
		given()
				.body("{\"name\": \"Bruce\", \"surname\": \"Banner\"}")
				.header("Content-Type", "application/json")
				.when()
				.post()
				.then()
				.statusCode(201);

		given()
				.when().get()
				.then()
				.statusCode(200)
				.body("$.size()", is(3),
						"[0].name", is("Bruce"),
						"[0].surname", is("Wayne"),
						"[1].name", is("Peter"),
						"[1].surname", is("Parker"),
						"[2].name", is("Bruce"),
						"[2].surname", is("Banner"));
	}

	@Inject HeroService service;
	@Test
	public void testService() {		
		int heros = service.getHeros().size();
		Assertions.assertEquals(heros, 2);
	}
}