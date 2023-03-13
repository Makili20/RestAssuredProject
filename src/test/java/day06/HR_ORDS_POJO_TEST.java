package day06;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Locations;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class HR_ORDS_POJO_TEST {
    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://44.199.212.141";
        RestAssured.port = 1000;
        RestAssured.basePath = "ords/hr";
    }
    @DisplayName("Testing the /Locations/{location_id} endpoint")
    @Test
    public void testLocation() {
      Response response=
              given()
                .accept(ContentType.JSON)
                .pathParam("location_id",1700)
                .log().all().
        when()
                .get("/locations/{location_id}")
                .prettyPeek();
        Locations l1=response.as(Locations.class);
        System.out.println("l1 = " + l1);

    }
    @DisplayName("Testing the /loctaion endpoint")
    @Test
    public void testLocations(){
        Response response=get("/locations").prettyPeek();
        //save all street address to List<String>
        List<String> addressList=response.jsonPath().getList("items.street_address");

        //save all Locations into List<Locations>

        List<Locations> locationsList=
                response.jsonPath().getList("items", Locations.class);
        System.out.println("locationsList = " + locationsList);

        for (Locations each : locationsList) {
            System.out.println(each);
        }

        // how do we assert we have 23 items in the list
        // using hamcrest library assertion to check the list with certain size
        //import static org.hamcrest.MatcherAssert.assertThat;
        //import static org.hamcrest.Matchers.hasSize;
        assertThat(locationsList, hasSize(23));
        // we want to get list of pojo but we only want to get those
        // data with country_id as US
        List<Locations> usLocations =
                response.jsonPath().getList("items.findAll { it.country_id=='US' }", Locations.class) ;
        //System.out.println("usLocations = " + usLocations);
        usLocations.forEach( eachLocation -> System.out.println(eachLocation));

    }


}
