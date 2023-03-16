package day10;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;
import utilities.LibraryUtil;

import static io.restassured.RestAssured.*;

public class LibraryAppReusingTheSpecification {

    static RequestSpecification reqSpec;
    static ResponseSpecification resSpec;

    @BeforeAll
    public static void init(){

        RestAssured.baseURI= ConfigurationReader.getProperty("library1.database.url");
        RestAssured.basePath="/rest/v1";
        String theToken= LibraryUtil.loginAndGetToken("librarian69@library","KNPXrm3S");
        //we have build a resuable request Specification for setting contentType
        reqSpec=given().accept(ContentType.JSON)//we waant Json back
                .log().all()
                .header("x-library-token",theToken);//we want to set the token header

        resSpec=expect().statusCode(200)
                .contentType(ContentType.JSON)//we are sending Content Type Json
                .logDetail(LogDetail.ALL);

    }
    @DisplayName("Testing GET/get book categories Endpoint with spec")
    @Test
    public void testLibrary(){
        given()
                .spec(reqSpec).
        when()
                .get("/get_book_categories").

        then()
                .spec(resSpec);


    }

    @DisplayName("Testing GET /get_all_users Endpoint with spec")
    @Test
    public void testGetAllUsers(){

        given()
                .spec(reqSpec).
                when()
                .get(" /get_all_users").
                then()
                .spec(resSpec)
        ;

    }

    @DisplayName("Testing GET /dashboard_stats Endpoint with spec")
    @Test
    public void testGet_Dashboard_stats(){

        given()
                .spec(reqSpec).
                when()
                .get(" /dashboard_stats").
                then()
                .spec(resSpec)
        ;

    }





}
