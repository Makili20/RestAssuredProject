package day5;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan2;
import utilities.SpartanTestBase;

import static io.restassured.RestAssured.given;

public class SpartanPojoGetRequestTest extends SpartanTestBase {

    @DisplayName("GET one spartan and convert it to Spartan Object")
    @Test
    public void test1(){
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200).log().body()
                .extract().response();

        //De serialize --> JSON to POJO(Java custom class)
        //2 different way to do this
        //1.using as() method
        //we convert json response to spartan object with the help of Jackson
        Spartan2 spartan15 = response.as(Spartan2.class);
        System.out.println(spartan15);

        System.out.println(spartan15.getName());
        System.out.println(spartan15.getPhone());

        //second way of deserialize to java
        //2.using JsonPath to deserialize to custom class
        JsonPath jsonPath = response.jsonPath();

        Spartan2 s15 = jsonPath.getObject("",Spartan2.class);
        System.out.println(s15.getName());
        System.out.println(s15.getGender());

    }
}