import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.get;



public class TestFiveHundred {


    @BeforeAll
    public static void setup() {
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
