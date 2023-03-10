package day5;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class HR_ORDS_Test {
    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://44.199.212.141";
        RestAssured.port = 1000;
        RestAssured.basePath = "ords/hr";
    }

    @DisplayName("Testing the /regions/{region_id} endpoint")
    @Test
    public void testRegion() {

        Response response = given()
                .log().all()
                .accept(ContentType.JSON)
                .pathParam("region_id", 1).
                when()
                .get("/regions/{region_id}")
                .prettyPeek();

    }
}
