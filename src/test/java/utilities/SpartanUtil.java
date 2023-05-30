package utilities;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojo.Spartan;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class SpartanUtil {

    public static Faker faker = new Faker();

    public static Map<String, Object> getRandomSpartanMap() {

        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("name", faker.name().firstName());
        bodyMap.put("gender", faker.demographic().sex());
        bodyMap.put("phone", faker.number().numberBetween(5_000_000_000L, 10_000_000_000L));

        return bodyMap;
    }

    public static Spartan createSpartan() {
        String name = faker.name().firstName();
        String genderCreate = faker.dog().gender();
        //female male  default I want to get Male and Female
        String gender = (genderCreate.charAt(0) + "").toUpperCase() + genderCreate.substring(1);
        long phone = Long.parseLong(faker.numerify("###########"));
        //new way for creating object
        Spartan spartan = Spartan.builder().gender(gender).name(name).phone(phone).build();
        //old way for creating object
        //  Spartan spartan = new Spartan(0, name, gender, phone);


        return spartan;
    }

    public static RequestSpecification login(String username, String password) {

        return given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().basic(username, password);


    }

    public static RequestSpecification postSpartan(String username, String password, Spartan spartan) {
        return login(username, password)
                .and()
                .body(spartan);

    }

    public static RequestSpecification getSpartan(String username, String password, int postSpartanId) {
        return   login(username, password)
                .and()
                .pathParam("id", postSpartanId);
    }
    public static RequestSpecification putSpartan(String username,String password,
                                                  Spartan spartan,int postSpartanId){
    return  login(username,password)
            .pathParam("id",postSpartanId)
            .body(spartan);






    }

    public static RequestSpecification deleteSpartan(String username, String password, int postSpartanId) {
   return   login(username,password)
           .pathParam("id",postSpartanId);


    }
}

