package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class SpartanTest_Parameters {

    @BeforeAll
    public static void setUp() {

        RestAssured.baseURI = "http://3.84.25.6:8000";
        RestAssured.basePath = "/api";
    }

    @DisplayName("testing/Spartans/{id}")
    @Test
    public void testSingleSpartan() {

        given()
                .log().all()
                .pathParam("id", 30).
                when()
                .get("spartans/{id}").
                then()
                .statusCode(is(200));


    }

    @DisplayName("testing2/Spartans/{id}")
    @Test
    public void testSingleSpartan2() {
        given()
                .log().all().
                when()
                .get("spartans/{id}", 30).
                then()
                .statusCode(is(200));
    }

    @DisplayName("Testing/spartans/{id} Body")
    @Test
    public void testSingleSpartanBody() {

        given()
                .log().all()
                .pathParam("id", 30).
                when()
                .get("spartans/{id}").
                then()
                .log().all()
                .statusCode(is(200))
                // .body("JSON PATH",is("THE VALUE"))
                .body("id", is(30))
                .body("name", is("Melania"))
                .body("gender", is("Female"));
    }

    @Test
    public void negativePathSpartanTest() {
        given().
                log().all().
                accept(ContentType.JSON)
                .pathParam("id", 500).
                when()
                .get("spartans/{id}").

                then()
                .statusCode(404)
                .and()
                .contentType(is("application/json"));


    }
    @Test
    public void QueryParam(){
        given().accept(ContentType.JSON)
                .and().queryParam("gender","Female")
                .and().queryParam("nameContains","J")
                .when().get("spartans/search")
                .then().statusCode(is(200));







    }
}