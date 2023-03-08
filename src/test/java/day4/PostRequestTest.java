package day4;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class PostRequestTest {
    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "http://44.199.212.141";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";

    }

    @DisplayName("Post request with String as body")
    @Test
    public void testPostWithStringBody() {
        Faker faker = new Faker();
        String randomName = faker.name().firstName();
        System.out.println("randomName = " + randomName);
        //let's try to get random names
        String myBodyData = "{\n" +
                "  \"name\"  : \" " + randomName + " \",\n" +
                "  \"gender\": \"Female\",\n" +
                "  \"phone\": 6234567890\n" +
                "}";
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(myBodyData).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201)
                .body("data.name", is(randomName));

    }

    @DisplayName("Posting with external json file")
    @Test
    public void testPostWithExternalFile() {
        // create a file object that point to spartan.json you just added
        // so we can use this as body in post request

        File file1 = new File("spartan.json");


        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(file1).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201)
                .body("data.name", is("From File"));
    }

    @DisplayName("Posting with Map object as body")
    @Test
    public void testPostWithMapAsBody() {

        // please add dependency jackson-databind

        // create a Map<String,Object> as hashMap
        // add name , gender , phone
        // Back at 3:00PM EST
        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("name", "Vincent");
        bodyMap.put("gender", "Male");
        bodyMap.put("phone", 3476346789l);

        System.out.println("bodyMap = " + bodyMap);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyMap).
        when()
                .post("/spartans").
         then()
                .log().all()
                .statusCode(201)
                .body("data.name",is("Vincent"));

    }
}
