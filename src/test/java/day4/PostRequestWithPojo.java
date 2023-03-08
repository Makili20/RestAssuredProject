package day4;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojo.Spartan;

import static io.restassured.RestAssured.given;

public class PostRequestWithPojo {


    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://44.199.212.141" ;
        RestAssured.port = 8000 ;
        RestAssured.basePath = "/api" ;

    }

    @Test
    public void testPostBodyWithPojo(){

        Spartan spartan1=new Spartan("Irinina Li","Female",1231231231);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartan1).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201);

    }


    }




