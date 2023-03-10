package day5;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Countries;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class Countries_test {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://44.199.212.141";
        RestAssured.port = 1000;
        RestAssured.basePath = "ords/hr";

    }

    @DisplayName("Testing the /countries/{country_id} endpoint")
    @Test
    public void testRegion() {

        Response response =
                given()
                        .log().all()
                        .accept(ContentType.JSON)
                        .pathParam("country_id", "US").
                        when()
                        .get("/countries/{country_id}")
                        .prettyPeek();

        Countries c1 = response.as(Countries.class);
        System.out.println("c1 = " + c1);

    }

    @DisplayName("Testing the /countries endpoint")
    @Test
    public void testCountriesJsonArrayToPojoList() {
        Response response = get("/countries").prettyPeek();

        JsonPath jp = response.jsonPath();

        System.out.println("First country_name " + jp.getString("items[0].country_name"));

        System.out.println("Second region_id " + jp.getInt("items[1].region_id"));

        System.out.println("Last region_id " + jp.getInt("items[-1].region_id"));


        // get the list of country name from the response and save it to List<String>

        List<Countries> allNames =jp.getList("items.country_name");
        System.out.println("allNames = " + allNames);
        // get a List<Countries> from the response json
        List<Countries>countriesList=jp.getList("items",Countries.class);
        System.out.println("countriesList = " + countriesList);


        for (Countries each : countriesList) {
            System.out.println("each = " + each);
        }
    }
}