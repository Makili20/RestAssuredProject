package day07;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanTestBase;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PutAndPatchRequestDemo extends SpartanTestBase {

    @DisplayName("PUT request to one spartan for update with Map")
    @Test
    public void test1() {
        //just like post request we have different opitons to send json body, we will go with map
        Map<String, Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("gender", "Male");
        putRequestMap.put("name", "Severus Snape");
        putRequestMap.put("phone", 8877445596l);

        //send a GEt request to id number and make sure fields are updated

       given()
                .contentType(ContentType.JSON)
                .body(putRequestMap).log().body()
                .and().pathParam("id",85)
                .when().put("/api/spartans/{id}")
                .then().statusCode(204);


    }


    @DisplayName("PATCH request to one spartan for update with Map")
    @Test
    public void test2(){
        //just like post request we have different opitons to send json body, we will go with map
        Map<String,Object> putRequestMap = new HashMap<>();

        putRequestMap.put("name","Severus");

        given().contentType(ContentType.JSON)
                .body(putRequestMap).log().body()
                .and().pathParam("id",85)
                .when().patch("/api/spartans/{id}")
                .then().statusCode(204);

        //HW
        //send a GET request to id number and make sure fields are updated

    }


    @DisplayName("DELETE one spartan")
    @Test
    public void test3(){

        int idToDelete =5;

       /* given().pathParam("id",idToDelete)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);


        */
        given().pathParam("id",idToDelete)
                .when().get("/api/spartans/{id}")
                .then().statusCode(404);



        //HW
        //we can send GET Request to id number and verify status code is 404

    }


}
