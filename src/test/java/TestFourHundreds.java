import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.get;



// In this class, we focus on bad requests, as the 4xx status codes represent client-side errors.
public class TestFourHundreds {



    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

    }


    @Test
    public void shouldReturn400(){
        // Used Mockoon to simulate 400 responses because jsonplaceholder does not return errors for certain conditions.

        Response response = given()
                .when()
                .post("http://localhost:3000/")
                .then()
                .log().all()
                .statusCode(400)
                .extract().response();

    }

    @Test
    public void shouldReturn404(){

        Response response = given()
                .header("Content-type", "application/json")
                .body("{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}")
                .log().all()
                .when()
                .post("/posts/1") // This must be "/post" not "/posts/1" because "/posts/1" is not a valid POST endpoint and will return 404.
                .then()
                .statusCode(404)
                .extract().response();

        System.out.println("\nStatus code: " + response.getStatusCode());




    }

}
