package day5;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Search;
import pojo.Spartan;
import pojo.Spartan2;
import utilities.SpartanTestBase;

import java.util.List;

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

    @DisplayName("GET one spartan from search endpoint and use POJO")
    @Test
    public void test2(){

        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().jsonPath();

        //get the second spartan from the content list and put inside the spartan object
        Spartan spartan = jsonPath.getObject("content[1]", Spartan.class);
        System.out.println(spartan.getName());
        System.out.println(spartan);

    }
    @DisplayName("GET one spartan from search endpoint and use POJO")
    @Test
    public void test3(){

        Response response = given()
                .accept(ContentType.JSON)
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().response();

        //get the full content json and convert it to Search object
        Search searchResult = response.as(Search.class);
        //we can also use jackson to get Search result
        // Search search2 = response.jsonPath().getObject("", Search.class);
        System.out.println(searchResult.getTotalElement());
        System.out.println(searchResult.getContent().get(1).getName());

    }

    @DisplayName("GET /api/spartans/search and save as List<Spartan>")
    @Test
    public void test4(){

        Response response = given()
                .accept(ContentType.JSON)
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        List<Spartan2> content = jsonPath.getList("content", Spartan2.class);

        System.out.println(content.get(1).getName());
    }


}