package day10;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;
import utilities.LibraryUtil;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class LibraryAppReusingTheSpecification_Shorter {


    @BeforeAll
    public static void init() {

        RestAssured.baseURI = ConfigurationReader.getProperty("library1.database.url");
        RestAssured.basePath = "/rest/v1";

        String theToken = LibraryUtil.loginAndGetToken("librarian69@library", "KNPXrm3S");
        //we have build a resuable request Specification for setting contentType
        RestAssured.requestSpecification = given().accept(ContentType.JSON)//we waant Json back
                .log().all()
                .header("x-library-token", theToken);//we want to set the token header

        RestAssured.responseSpecification = expect().statusCode(200)
                .contentType(ContentType.JSON)//we are sending Content Type Json
                .logDetail(LogDetail.ALL);

    }

    @DisplayName("Testing GET/get book categories Endpoint with spec")
    @Test
    public void testLibrary() {

        when()
                .get("/get_book_categories");




    }

    @DisplayName("Testing GET /get_all_users Endpoint with spec")
    @Test
    public void testGetAllUsers() {


        when()
                .get(" /get_all_users");


    }
//get the Map<String,String>object out of the response of Get/dashboard stats
    @DisplayName("Testing GET /dashboard_stats Endpoint with spec")
    @Test
    public void testGet_Dashboard_stats() {

     Response response= when()
                .get(" /dashboard_stats").prettyPeek();
     //if here is no path needed t get to what you are looking for
        //or if you wanted to polint to your entire response,you can just provide ""

        Map<String,Object> statMap= response.jsonPath().getMap("");
        System.out.println("statMap = " + statMap);

    }
}





