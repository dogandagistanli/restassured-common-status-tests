import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BeforeClassJson {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

}
