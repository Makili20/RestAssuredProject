package day4;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

public class PutAndPatchRequestTest {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://44.199.212.141" ;
        RestAssured.port = 8000 ;
        RestAssured.basePath = "/api" ;

    }
    @DisplayName("Put Request body as a Map")
    @Test
    public void testPutRequestWithMap(){
        //put request to update spartan with id 421
        //name: put with map,gender :male,phone: 1231231231
        //status code:204
        //how do I actaully know that is updated since it does not have body in request
        //we can make another get request right afte this and assert the body
        String randomName=new Faker().name().firstName();
        Map<String,Object> updateBody=new LinkedHashMap<>();
        updateBody.put("name",randomName);
        updateBody.put("gender","Male");
        updateBody.put("phone",8745124312L);
        //this is how we provide d=the body with POJO

        Spartan spartan1=new Spartan(randomName,"Female",1231231231L);

        given()
                .log().all()
                .contentType(ContentType.JSON)
               // .body(updateBody).
                       .body(spartan1).
        when()
                .put("/spartans/{id}",5).
        then()
                .log().all()
                .statusCode(204);
    }

    @DisplayName("Put Request body with POJO")
    @Test
    public void testPutRequestWithPojo(){
        //put request to update spartan with id 421
        //name: put with map,gender :male,phone: 1231231231
        //status code:204
        //how do I actaully know that is updated since it does not have body in request
        //we can make another get request right afte this and assert the body
        String randomName=new Faker().name().firstName();

        Spartan spartan1=new Spartan(randomName,"Female",1231231231L);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                // .body(updateBody).
                .body(spartan1).
                when()
                .put("/spartans/{id}",5).
                then()
                .log().all()
                .statusCode(204);

        //MAKING ANOTHER GET REQUEST TO MAKE IT SURE THAT WORKS !!!!

        when()
                .get("spartans/{id}",5).

         then()
                .log().all()
                .statusCode(200)
                .body("name",is(randomName));

    }

    @DisplayName("Patch request")
    @Test
    public void testPatchPartialUpdate() {

        //ONLY UPDATE THE NAME WITH FAKER
        String randomName = new Faker().name().firstName();
        String patchBody = "\"name\" : \" " + randomName + "\" ";
        Map<String, Object> patchBodyMap = new HashMap<>();
        patchBodyMap.put("name", randomName);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(patchBodyMap).
                when()
                .patch("/spartans/{id}", 142).
                then()
                .log().all()
                .statusCode(204);
        // MAKE ANOTHER GET REQUEST HERE TO MAKE SURE IT WORKED
        when()
                .get("spartans/{id}",142).

                then()
                .log().all()
                .statusCode(200)
                .body("name",is(randomName));


        // CREATE A METHOD THAT POST A RANDOM SPARTAN TO THE SERVER
        // AND RETURN THE ID OF THAT SPARTAN , SO YOU CAN ALWAYS USE A DATA THAT EXISTS

    }}







