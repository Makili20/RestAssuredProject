package day10;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Category;
import pojo.User;
import utilities.ConfigurationReader;
import utilities.LibraryUtil;

import java.util.List;
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

       Response response= when()
                .get("/get_book_categories");
       List<Category> categoryList=response.jsonPath().getList("");
        System.out.println("categoryList = " + categoryList);


        // above code is great , but what if I wanted to
        // store each category as map rather than pojo
        // Each category is key value pair --->> Map
        // and we have many category  --->> List<Map>
        // jsonPath methods always try to help to convert the types where it can
        // in this case , each category in jsonArray we tryied to store into map then get a list out of it
        // and Jackson databind take care of all conversion
        //List< Map<String,String> > categoryMapList = response.jsonPath().getList("");
        List< Map<Integer,String> > categoryMapList = response.jsonPath().getList("");
        System.out.println("categoryMapList = " + categoryMapList);

    }

    @DisplayName("Testing GET /get_all_users Endpoint with spec")
    @Test
    public void testGetAllUsers() {


     Response response=when().get(" /get_all_users");
        JsonPath jp=response.jsonPath();
        List<User> allUserList=jp.getList("",User.class);

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





