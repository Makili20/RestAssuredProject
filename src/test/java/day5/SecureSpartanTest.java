package day5;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;

import static io.restassured.RestAssured.given;

public class SecureSpartanTest {
    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://44.199.212.141" ;
        RestAssured.port = 8000 ;
        RestAssured.basePath = "/api" ;

    }
    //add @BeforeAll and use the Spartan App ID with basic auth

    //make a simple get request /spartans/{id}

    @DisplayName("Test GET /spartan/{id} Endpoint with No Credentials")
    @Test
    public void testGetSingleSpartanSecured(){

        given()
                .log().all()
                .pathParam("id",174).
                when()
                .get("/spartans/{id}").
                then()
                .log().all()
                .statusCode( 401 )
        ;

    }

    @DisplayName("Test GET /spartan/{id} Endpoint with Credentials")
    @Test
    public void testGettingSpartanWithCredentials(){

        int newId = createRandomSpartan();

        given()
                .log().all()
                .auth().basic("admin","admin")
                .pathParam("id",newId).
                when()
                .get("/spartans/{id}").
                then()
                .log().all()
                .statusCode(200) ;

    }

    public static int createRandomSpartan(){
        // use faker to get random first name , gender, and 10 digit number for phone
        Faker faker = new Faker();
        String name = faker.name().firstName();
        String gender = faker.demographic().sex();
        long phone = faker.number().numberBetween(1000000000L,9999999999L);
        // this what we are going pass for post body
        Spartan sp = new Spartan(name, gender, phone) ;
        // make a post request with random data
        Response response = given()
                .log().ifValidationFails() // only log if anything goes wrong
                .auth().basic("admin","admin")
                .contentType(ContentType.JSON)
                .body(sp).
                when()
                .post("/spartans")
                .prettyPeek();
        // use jsonPath to get the id from the response and return it out from the method
        return response.jsonPath().getInt("data.id") ;
    }


}
