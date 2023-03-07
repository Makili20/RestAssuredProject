package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class SpartanTest {

    @DisplayName("Get All Spartan")
    @Test
    public void testAllSpartans() {
        // String spartanURL= "http://44.199.212.141:8000";
        //hoe to set base URL,port,base path so I can reuse them

        //i want to explicitly specify I want json response from this result

        RestAssured.baseURI = "http://44.199.212.141:8000";
        RestAssured.basePath = "/api";

        given()
                .header("Accept", "application/json").
                when()
                .get("/spartans").
                then()
                .statusCode(is(200));
    }
    @DisplayName("Get All Spartan 2")
    @Test
    public void testAllSpartans2(){
        //send the same request specyfing the accept header is application/json
        //use baseurl basepath,check status code 200,contentType header is json

        given()
                .baseUri("http://44.199.212.141:8000")
                .basePath("/api")
                .accept(ContentType.JSON).
       when()
                .get("/spartans").
        then()
                .statusCode(is(200))
                //.contentType("application/json; charset=utf-8");
                // .contentType(is("application/json; charset=utf-8"));
                .contentType(ContentType.JSON);


    }

}
