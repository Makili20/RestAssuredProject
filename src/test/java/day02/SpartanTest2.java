package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class SpartanTest2 {

    @BeforeAll
    public static  void setUp(){

        RestAssured.baseURI = "http://44.199.212.141:8000";
        RestAssured.basePath = "/api";
    }

    @DisplayName("Get 1 Spartan Test")
    @Test
    public void testSingleSpartan(){

        //I want to log the request I sent so I see what is the URL,methods and so on

        given()
                .log().all().
        when()
                .get("/spartans/3").
               // .prettyPeek().
       then()
                .log().all()
                //.log().body()
               // .log().ifValidationFails()
                .statusCode(is(200))

                ;

    }

    // quick tasks
    // add another test for hello endpoint by reusing the baseURI , basePath above
    // specify you want to get a text result back
    // check status code is 200
    // contentType is Text
    // body is Hello from Sparta
    @DisplayName("Testing /Hello again")
    @Test
    public void testHello(){

        given()
                .accept(ContentType.TEXT). // specify you want to get a text result back
                when()
                .get("/hello").  // sending request to baseURI+basePath+ /hello
                then()
                .statusCode( is(200) )  // checking status code 200
                .contentType( ContentType.TEXT)  // checking content type is text
                .body(equalTo("Hello from Sparta")) ; // checking the body

    }



    @DisplayName("Get All Spartan")
    @Test
    public void testAllSpartans() {
        // String spartanURL= "http://44.199.212.141:8000";
        //hoe to set base URL,port,base path so I can reuse them

        //i want to explicitly specify I want json response from this result

        given()
                .header("Accept", "application/json").
                when()
                .get("/spartans").
                then()
                .statusCode(is(200));
    }
    @DisplayName("Get All Spartan 2")
    @Test
    public void testAllSpartans2(){
        //send the same request specyfing the accept header is application/json
        //use baseurl basepath,check status code 200,contentType header is json

        given()
                //.baseUri("http://44.199.212.141:8000")
                //.basePath("/api")
                .accept(ContentType.JSON).
       when()
                .get("/spartans").
        then()
                .statusCode(is(200))
                //.contentType("application/json; charset=utf-8");
                // .contentType(is("application/json; charset=utf-8"));
                .contentType(ContentType.JSON);


    }

}
