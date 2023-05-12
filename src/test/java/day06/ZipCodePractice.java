package day06;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.ZipCode;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
public class ZipCodePractice {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://api.zippopotam.us";
    }

    @DisplayName("Get request to /us endpoint")
    @Test
    public void test1() {

        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("zip", 22031).
                when()
                .get("/us/{zip}")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .header("Server", "cloudflare")
                .header("Report-To", notNullValue())
                .extract().jsonPath();

        ZipCode zipCode = jsonPath.getObject("", ZipCode.class);
        System.out.println("zipCode.getPost_code() = " + zipCode.getPost_code());
        System.out.println("zipCode.getCountry() = " + zipCode.getCountry());
        System.out.println("zipCode.getCountry_abbreviation() = " + zipCode.getCountry_abbreviation());
        System.out.println("zipCode.getPlaces().getPlace_name() = " + zipCode.getPlaces().get(0).getPlace_name());
        // System.out.println("zipCode.getPlaces().getState() = " + zipCode.getPlaces().get(0).getState());
        //  System.out.println("zipCode.getPlaces().getLatitude() = " + zipCode.getPlaces().get(0).getLatitude());

    }

    @DisplayName("Negative get request test")
    @Test
    public void negativeTest() {
        given().accept(ContentType.JSON)
                .and()
                .pathParam("zip", 50000)
                .when()
                .get("/us/{zip}")
                .then()
                .statusCode(404)
                .contentType("application/json");
    }

    @DisplayName("Another get request with ZipCode")
    @Test
    public void test2() {
       Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("state", "va")
                .pathParam("city", "fairfax").
                when()
                .get("/us/{state}/{city}").
                then()
                .statusCode(200)
                .contentType("application/json")
               .extract().response();





      /*  ZipCountry zipCountry=jsonPath.getObject("", ZipCountry.class);

        String expectedCountryA=zipCountry.getCountry_abbreviation();
        assertThat(expectedCountryA,is("US"));
        String expCountry=zipCountry.getCountry();
        assertThat(expCountry,is("United States"));
        String placeName=zipCountry.getPlaces().get(0).getPlace_name();

        assertThat(placeName,is("Fairfax"));
        String postCode=zipCountry.getPlaces().get(0).getPost_code();
        assertTrue(postCode.startsWith("22"));

        System.out.println(expCountry);
        System.out.println(placeName);
        System.out.println(postCode);

    }

       */
    }
}