package day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class PutRequestExample {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://44.199.212.141" ;
        RestAssured.port = 8000 ;
        RestAssured.basePath = "/api" ;

    }

    @DisplayName("Updating existing Data")
    @Test
    public void updateSpartan(){

        String updatedBody = "{\n" +
                "        \"id\": 809,\n" +
                "        \"name\": \"Wonder Woman\",\n" +
                "        \"gender\": \"Female\",\n" +
                "        \"phone\": 1234567890\n" +
                "       }" ;

        given()
                .contentType(ContentType.JSON)
                .body(updatedBody)
                .log().all().
                when()
                .put("/spartans/{id}",809).
                then()
                .log().all()
                .statusCode(204) ;

    }
    @DisplayName("Delete Data using Delete method")
    @Test
    public void testDelete(){

        when()
                .delete("/spartans/{id}",756).
                then()
                .statusCode(204)
        ;

    }

}
