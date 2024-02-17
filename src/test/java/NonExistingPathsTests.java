import io.restassured.RestAssured;
import org.junit.jupiter.params.provider.Arguments;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class NonExistingPathsTests {
    @ParameterizedTest
    @MethodSource("getAllNotFoundPaths")
    public void getAllSets_forValidParametersGiven_shouldReturnOk(String path){
        RestAssured.baseURI = "https://api.magicthegathering.io/v1";
        given()
                .when()
                .get(path)
                .then()
                .statusCode(404);
    }

    public static Stream<Arguments> getAllNotFoundPaths(){
        return Stream.of(
                Arguments.of("/xd"),
                Arguments.of("/lalal"),
                Arguments.of("/asdasd"),
                Arguments.of("/cards/386616/test"),
                Arguments.of("/sets/ktk/mq")
        );
    }
}
