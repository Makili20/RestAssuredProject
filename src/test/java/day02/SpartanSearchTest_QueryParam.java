package day02;


import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class SpartanSearchTest_QueryParam {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "http://44.199.212.141:8000" ;
        RestAssured.basePath = "/api" ;

    }
    //http://54.174.216.245:8000/api/spartans/search?gender=male&nameContains=ea
    @DisplayName("Testing /spartans/search Endpoint")
    @Test
    public void testSearch(){

        given()
                .log().all()
                .queryParam("gender","male")
                .queryParam("nameContains","a").
                when()
                .get("spartans/search").
                then()
                .log().all()
                .statusCode(200)
                // assert number of elements according to your result here
                .body("totalElement", is(78) )
        ;


    }


}
