package day3;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.HrTestBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HrPractice extends HrTestBase {

    /*
      Given accept type is Json
      And parameters: q = {"region_id":2}
      When users sends a GET request to "/countries"
      Then status code is 200
      And Content type is application/json
      And Payload should contain "United States of America"
   */
    @DisplayName("GET rquest to /countries")
    @Test
    public void test1() {

        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .queryParam("q", "{\"region_id\": 2}")
                .log().all().
                when()
                .get("/countries")
                .prettyPeek();


        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("United States of America"));

    }

    /*
      send a GET request o employees endpoint, filter only Job_id IT_PROG
      then assert that all job_ids are IT_PROG
   */
    @DisplayName("GET request to /employees with Query Param")
    @Test
    public void test2() {
        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when()
                .get("/employees");

        assertEquals(200, response.statusCode());
        //assert all the jobsids are IT_PROG
        List<String> jobIds = response.path("items.job_id");
        for (String each : jobIds) {
            assertEquals(each, "IT_PROG");
        }

    }

    @DisplayName("Request with query parameters")
    @Test
    public void test3() {
        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("q", "{\"department_id\":\"80\"}")
                .when()
                .get("/employees");
        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        JsonPath jsonPath = response.jsonPath();

        List<String> allJobId = jsonPath.getList("items.job_id");
        for (String jobId : allJobId) {
            assertTrue(jobId.startsWith("SA"));

        }

        List<Integer> dept_ID = jsonPath.getList("items.department_id");
        System.out.println("dept_ID = " + dept_ID);
        for (Integer each : dept_ID) {

            assertEquals(80, each);

        }
        int count = jsonPath.getInt("count");
        System.out.println("count = " + count);
        assertEquals(25, count);

    }

    /*Q3:
- Given accept type is Json
-Query param value q= region_id 3
- When: users sends request to /countries
- Then status code is 200
- And all regions_id is 3
- And count is 6
- And hasMore is false
- And Country_name are;
Australia,China,India,Japan,Malaysia,Singapore

     */
    @DisplayName("Another test with query param")
    @Test
    public void test4() {
        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\": 3}").
                when()
                .get("/countries")
                .prettyPeek();
        assertEquals(200, response.statusCode());
        JsonPath jsonPath = response.jsonPath();

        List<Integer> regionsIds = jsonPath.getList("items.region_id");
        for (Integer regionId : regionsIds) {
            assertTrue(regionId.equals(3));
        }
        int count = jsonPath.getInt("count");
        System.out.println("count = " + count);
        assertEquals(6, count);

        boolean hasMore = jsonPath.getBoolean("hasMore");
        assertEquals(false, hasMore);

        List<String> expectedCountryNames = new ArrayList<>();
        expectedCountryNames.addAll(Arrays.asList("Australia", "China", "India", "Japan", "Malaysia", "Singapore"));
        List<String> actualCountryName = jsonPath.getList("items.country_name");

        assertEquals(expectedCountryNames, actualCountryName);
    }

/*
    @DisplayName("Q3")
    @Test
    public void test3() {

   RestAssured.given().accept(ContentType.JSON).
   queryParam("q","{\"region_id\":3}").
    when().
     get("/countries").
    then().
    statusCode(200).
    body("items.region_id",everyItem(equalTo(3)),"count",equalTo(6),
    "hasMore",equalTo(false),"items.country_name", hasItems("Australia", "China", "India", "Japan", "Malaysia", "Singapore")) ;



    }

 */

}
