
import io.restassured.RestAssured;
import org.junit.jupiter.params.provider.Arguments;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertTrue;

public class FormatsTests {
    @Test
    public void getAllFormats_ShouldReturnOkWIthProperBody(){
        RestAssured.baseURI = "https://api.magicthegathering.io/v1";
        Response response = given()
                .when()
                .get("/formats");

        response.then()
                .statusCode(200);

        //the second assertion checks if the response actually returns types
        assertTrue(response.jsonPath().getList("formats").size() > 0);
    }
}
