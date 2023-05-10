package day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.HrTestBase;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class HrTestWithParams extends HrTestBase {

   @Test
    public void test1(){
       Response response=get("/regions");
       response.prettyPeek();
   }

       /*
        Given accept type is Json
        And parameters: q = {"region_id":2}
        When users sends a GET request to "/countries"
        Then status code is 200
        And Content type is application/json
        And Payload should contain "United States of America"
     */
    @DisplayName("GET request to /countries with query param")
    @Test
    public void test2(){
       Response response= given()
                .accept(ContentType.JSON)
                .and()
                .queryParam("q","{\"region_id\": 2}")
                .log().all().
        when()
                .get("/countries");

        //verify status code
        assertEquals(200,response.statusCode());
        //verify contentb type
        assertEquals("application/json",response.contentType());
        //verify United States of America exists
        assertTrue(response.body().asString().contains("United States of America"));
    }
    @DisplayName("GET request to countries with Path method")
    @Test
    public void test3(){
        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}")
                .when()
                .get("/countries");

        //response.prettyPrint();
        //print limit result
        System.out.println(response.path("limit").toString());
        //print hasMore
        System.out.println(response.path("hasMore").toString());
        //print second country id
        System.out.println(response.path("items[1].country_id").toString());
        //print 4th element country name
        System.out.println(response.path("items[3].country_name").toString());

        //get me all country names
        List<String> countryNames= response.path("items.country_name");
        System.out.println(countryNames);

        //assert that in the response all region_ids are 2
        //save all the regions ids inside the list
        List<Integer>  allRegionsIDs = response.path("items.region_id");

        //assert one by one that they are equal to 2
        for (Integer regionsID : allRegionsIDs) {
            assertEquals(2,regionsID);
        }
    }

    /*
        send a GET request o employees endpoint, filter only Job_id IT_PROG
        then assert that all job_ids are IT_PROG
     */
    @DisplayName("GET request to /employees with Query Param")
    @Test
    public void test4(){
        Response response = given()
                .accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when()
                .get("/employees");

        assertEquals(200,response.statusCode());

        //assert all the jobsids are IT_PROG
        List<String> allJobIDs = response.path("items.job_id");

        //verify each of them is IT_PROG
        for (String jobID : allJobIDs) {
            assertEquals("IT_PROG",jobID);
        }

    }
}
