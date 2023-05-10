package day5;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanTestBase;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class JsonToJavaTest extends SpartanTestBase {
    @DisplayName("GET one Spartan and deserialize to MAP")
    @Test
    public void test1() {

        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .extract().response();

        //get the json body and convert it to java map
        Map<String,Object> jsonMap=response.as(Map.class);
        System.out.println(jsonMap);
        String name=(String)jsonMap.get("name");
        //verify name is Meta
        assertThat(name, Matchers.is("name"));
    }
    @DisplayName("GET all spartans to JAVA data structure")
    @Test
    public void getAllSpartan(){

        Response response = get("/api/spartans").then().statusCode(200).extract().response();

        //we need to convert json to java which is deserialize

        List<Map<String,Object>> jsonList = response.as(List.class);
        //51
        System.out.println(jsonList.get(1).get("name"));

    }

}