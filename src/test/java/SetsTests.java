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

public class SetsTests {
    @Test
    public void getSetById_forExistingSet_shouldReturnOkStatusCode() {
        RestAssured.baseURI = "https://api.magicthegathering.io/v1";
        Response response = given()
                .when()
                .get("/sets/ktk");
        response.then()
                .statusCode(200);
        assertTrue(response.jsonPath().getMap("").containsKey("set"));
    }

    @Test
    public void getSetById_forNonExistingSet_shouldReturnNotFoundStatusCode() {
        RestAssured.baseURI = "https://api.magicthegathering.io/v1";
        given()
                .when()
                .get("/sets/-1")
                .then()
                .statusCode(404);
    }

    @ParameterizedTest
    @MethodSource("getAllValidParameters")
    public void getAllSets_forValidParametersGiven_shouldReturnOk(String paramName, String paramVal){
        RestAssured.baseURI = "https://api.magicthegathering.io/v1";
        given()
                .when()
                .queryParam(paramName, paramVal)
                .get("/sets")
                .then()
                .statusCode(200);
    }

    public static Stream<Arguments> getAllValidParameters(){
        return Stream.of(
                Arguments.of("page", "10"),
                Arguments.of("name", "khans|origins"),
                Arguments.of("pageSize", "50")
        );
    }

    @Test
    public void getBoosterBySetId_forExistingSet_shouldReturnOk(){
        RestAssured.baseURI = "https://api.magicthegathering.io/v1";
        given()
                .when()
                .get("/sets/ktk/booster")
                .then()
                .statusCode(200);
    }

    @Test
    public void getBoosterBySetId_forNonExistingSet_shouldReturnNotFound(){
        RestAssured.baseURI = "https://api.magicthegathering.io/v1";
        given()
                .when()
                .get("/sets/-1/booster")
                .then()
                .statusCode(404);
    }
}
