package day3;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Practice1 {

    @BeforeAll
    //in Junit5 this methgod is static
    //is we not make it sttaic it will not work because thats how it's defined in the doc
    public static void inti(){
        RestAssured.baseURI= "http://44.199.212.141";
        RestAssured.port=8000;
        RestAssured.basePath="/api";
    }

    @DisplayName("")
    @Test
    public void test1(){

        given()
                .log().all()
                .queryParam("gender","Female").
        when()
                .get("/spartans/search").
        then()
                .statusCode(200);


    }
}
