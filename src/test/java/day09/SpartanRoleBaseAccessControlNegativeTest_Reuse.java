package day09;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;

public class SpartanRoleBaseAccessControlNegativeTest_Reuse {
static RequestSpecification requestSpec;
static ResponseSpecification responseSpec;
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://44.199.212.141";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";
    }

    @DisplayName("User should not be able to delete data")
    @Test
    public void testUserCanNotDeleteData(){

        // building reusable request specification
        // using RequestSpecBuilder class

         requestSpec = given()
                .auth().basic("user", "user")
                .accept(ContentType.JSON) // we are getting 403 with json body so accept header is json
                .log().all();

        // Extracting ResponseSpecification for all assertions so we can reuse
        // We will be using a class called ResponseSpecBuilder
        ResponseSpecBuilder resSpecBuilder = new ResponseSpecBuilder() ;
        // Getting the reusable ResponseSpecification object using the builder methods chaining
        responseSpec = resSpecBuilder
                .expectStatusCode(403)
                .expectContentType(ContentType.JSON)
                .expectHeader("Date", notNullValue(String.class)  )
                .log(LogDetail.ALL)
                .build(); // build method will return ResponseSpecification

        // expectHeader second argument expect a Matcher<String>
        // but notNullValue() return a Matcher<Object> so it did not compile
        // so we used the second version of notNullValue( The Matcher type inside <>)
        // to specify what kind of matcher we wanted

        given()
                .spec(requestSpec).
                when()
                .delete("/spartans/{id}" , 10).
                then()
                .spec(responseSpec);

    }

    @DisplayName("User should not be able to update data")
    @Test
    public void testUserCanNotUpdateData(){

        Spartan spartanObj = new Spartan("some name", "Male", 8888888888L) ;

        given()
                .auth().basic("user","user")
                .accept(ContentType.JSON)
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartanObj).
                when()
                .put("/spartans/{id}", 10).
                then()
                .statusCode(403)
                .contentType(ContentType.JSON)
                .header("Date", is( notNullValue() ) ) // checking Date header is not null
                .log().all();


    }

    @DisplayName("User should not be able to post data")
    @Test
    public void testUserCanNotPostData(){

        Spartan spartanObj = new Spartan("some name", "Male", 8888888888L) ;

        given()
                .auth().basic("user","user")
                .accept(ContentType.JSON)
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartanObj).
                when()
                .post("/spartans").
                then()
                .statusCode(403)
                .contentType(ContentType.JSON)
                .header("Date", is( notNullValue() ) ) // checking Date header is not null
                .log().all();

    }

}
