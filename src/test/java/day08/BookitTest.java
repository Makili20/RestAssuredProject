package day08;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class BookitTest {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI ="https://cybertek-reservation-api-qa.herokuapp.com";

    }
    String accessToken="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxODMxNiIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ.AfWgzpG2pL-Sdq099hoRVsADQwTTrj6tCtYddW1wIRM";

    @DisplayName("GET/api/campuses")
    @Test
    public void test1(){
        //how to pass bearer token for bookit ?

        given()
                .header("Authorization",accessToken)
                .accept(ContentType.JSON)
                .when()
                .get("/api/campuses")
                .then().statusCode(200)
                .log().all();

    }

    @DisplayName("GET /api/users/me")
    @Test
    public void test2(){
        given()
                .header("Authorization",accessToken)
                .accept(ContentType.JSON)
                .when()
                .get("/api/users/me")
                .then().statusCode(200)
                .log().all();


    }

    //create bookitUtil class
    //create static method that accepts email and password, and return token with "Bearer your-token"


}
