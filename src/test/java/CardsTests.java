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

public class CardsTests {

    @Test
    public void getCardById_forExistingCard_shouldReturnOkStatusCode() {
        RestAssured.baseURI = "https://api.magicthegathering.io/v1";
        Response response = given()
                .when()
                .get("/cards/386616");
        response.then()
                .statusCode(200);

        // here I check if the JSON response contains an object named "card"
        assertTrue(response.jsonPath().getMap("").containsKey("card"));
    }

    @Test
    public void getCardById_forNonExistingCard_shouldReturnNotFoundStatusCode(){
        RestAssured.baseURI = "https://api.magicthegathering.io/v1";
        given()
                .when()
                .get("/cards/-1")
                .then()
                .statusCode(404);
    }
}
