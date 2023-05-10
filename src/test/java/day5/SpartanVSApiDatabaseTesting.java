package day5;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.DBUtils;
import utilities.SpartanTestBase;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class SpartanVSApiDatabaseTesting extends SpartanTestBase {
    @DisplayName("Compare one spartan information vs db")
    @Test
    public void test1(){
       Response response= given()
                .accept(ContentType.JSON)
                .pathParam("id",15)
               .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200).extract().response();
//how to convert json response to Map
        Map<String,Object> apiMap=response.as(Map.class);
        System.out.println(apiMap.toString());
        //we need to get information from database
        //which database we will conncet? Oracle
        //weneed connection String of spartan db

        String query="SELECT spartan_id,name,gender,phone\n"+
        "FROM SPARTANS\n"+
        "where spartan_id=15";
        Map<String, Object> dbMap= DBUtils.getRowMap(query);
        System.out.println(dbMap);
        //compare api vs database
        assertThat(apiMap.get("id").toString(), Matchers.is(dbMap.get("SPARTAN_ID").toString()));
        assertThat(apiMap.get("name"), Matchers.is(dbMap.get("NAME")));
        assertThat(apiMap.get("gender"), Matchers.is(dbMap.get("GENDER")));
        assertThat(apiMap.get("phone").toString(), Matchers.is(dbMap.get("PHONE").toString()));
    }



}
