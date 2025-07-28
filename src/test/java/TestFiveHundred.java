import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestFiveHundred {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void shouldReturn500() {
        Response response = given()
                .header("Content-type", "application/json")
                .body("{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1") // Missing opening '{' in JSON payload
                .log().all()
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(500)
                .extract().response();
    }

}
