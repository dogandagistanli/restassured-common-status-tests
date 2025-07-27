import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.get;


public class TestTwoHundreds {

    private static final Logger log = LoggerFactory.getLogger(TestTwoHundreds.class);

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

    }

    @Test
    public void shouldReturn200() {
        /* This test verifies that the server successfully processes the request and returns a 200 OK status with a response body.
        Common for successful GET requests or PUT requests where the server returns updated data or a message.*/

        given().
                when().
                get("/posts/1").
                then().
                log().all().
                assertThat().
                body("id", equalTo(1))
                .extract().response();
        Response response = get("https://jsonplaceholder.typicode.com/posts/1");
        // We can capture the response this way to perform further validations or processing or the way in the shouldReturn201.
        System.out.println("Status code: " + response.getStatusCode());

    }

    @Test
    public void shouldReturn201() {
        /* This test verifies that the server returns a 201 Created status when a new resource is successfully created.
        Typically used in POST requests when the server creates and stores a new object.*/

        Response response = given()
                .header("Content-type", "application/json")
                .body("{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}")
                .log().all()
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201)
                .extract().response();

        System.out.println("\nStatus code: " + response.getStatusCode());

    }

    @Test
    public void shouldReturn200Put() {
        /* This test sends a PUT request to update a post and expects a 200 OK status,ß
        indicating the resource was successfully processed and a response body was returned.
        The test also checks if the 'id' field in the response matches the expected value.*/

        Response response = given()
                .header("Content-type", "application/json; charset=UTF-8")
                .body("{\"title\": \"updated\", \"body\": \"updated body\", \"userId\": 1}")
                .when()
                .put("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200)
                .log().all()
                .assertThat()
                .body("id", equalTo(1))
                .extract().response();

        System.out.println("\nStatus code: " + response.getStatusCode());

        }

    @Test
    public void shouldReturn204Delete() {
        /* I have used Mockoon to mock the DELETE /api/users/2 endpoint.
        This test verifies that the server responds with HTTP 204 No Content.*/

        Response response = given()
                .when()
                .delete("http://localhost:3000/api/users/2")
                .then()
                .statusCode(204)
                .log().all()
                .extract().response();

        System.out.println("\nStatus code: " + response.getStatusCode());
    }





}









