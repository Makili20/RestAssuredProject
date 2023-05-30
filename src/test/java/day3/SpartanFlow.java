package day3;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;
import pojo.Spartan;
import utilities.SpartanAuthTestBase;
import utilities.SpartanUtil;

import static org.hamcrest.Matchers.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class SpartanFlow extends SpartanAuthTestBase {

    /*
       As a homework,write a detailed test for Role Base Access Control(RBAC)
           in Spartan Auth app (7000)
           Admin should be able take all CRUD
           Editor should be able to take all CRUD
               other than DELETE
           User should be able to only READ data
               not update,delete,create (POST,PUT,PATCH,DELETE)
      --------------------------------------------------------
       Can guest even read data ? 401 for all

    */
//post
    static int postSpartanId;
    static String postSpartanName;
    static String postSpartanGender;
    static long postSpartanPhone;

    @Order(1)
    @Test
    public void postSpartan() {
        Spartan spartan = SpartanUtil.createSpartan();
        JsonPath jsonPath = SpartanUtil.postSpartan("admin", "admin", spartan)
                .log().uri()
                .post("/spartans")
                .then().statusCode(201)
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!"))
                .extract().jsonPath();

        postSpartanId = jsonPath.getInt("data.id");
        postSpartanName = jsonPath.getString("data.name");
        postSpartanGender = jsonPath.getString("data.gender");
        postSpartanPhone = jsonPath.getLong("data.phone");
        System.out.println("postSpartanId = " + postSpartanId);
        System.out.println("post spartan = " + spartan);
    }


    //get Spartan
    @Order(2)
    @Test
    public void getSpartan() {
        JsonPath jsonPath = SpartanUtil.getSpartan("admin", "admin", postSpartanId)
                .log().uri()
                .get("/spartans/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().jsonPath();
        System.out.println("get Spartan = " + jsonPath.getJsonObject("").toString());

        System.out.println("get Spartan = " + jsonPath.getJsonObject("").toString());
        int getSpartanId = jsonPath.getInt("id");
        System.out.println("getSpartanId = " + getSpartanId);
        String getSpartanName = jsonPath.getString("name");
        String getSpartanGender = jsonPath.getString("gender");
        long getSpartanPhone = jsonPath.getLong("phone");

        Assertions.assertEquals(postSpartanId, getSpartanId);
        Assertions.assertEquals(postSpartanName, getSpartanName);
        Assertions.assertEquals(postSpartanGender, getSpartanGender);
        Assertions.assertEquals(postSpartanPhone, getSpartanPhone);


    }
    @Order(3)
    @Test
    public void putSpartan(){
        Spartan spartan=SpartanUtil.createSpartan();
        SpartanUtil.putSpartan("admin","admin",spartan,postSpartanId)
                .and()
                .put("/spartans/{id}")
                .then()
                .statusCode(204);
    }
    @Order(4)
    //get spartan
    @Test
    public void getSpartanAfterPut() {
        JsonPath jsonPath = SpartanUtil.getSpartan("admin", "admin", postSpartanId)
                .log().uri()
                .get("/spartans/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().jsonPath();

        System.out.println("get Spartan after put = " + jsonPath.getJsonObject("").toString());
        int getSpartanId = jsonPath.getInt("id");
        System.out.println("getSpartanId = " + getSpartanId);

    }

//put spartan

//delete spartan

    //delete spartan
    @Order(5)
    @Test
    public void deleteSpartan(){
        SpartanUtil.deleteSpartan("admin","admin",postSpartanId)
                .delete("/spartans/{id}")
                .then()
                .statusCode(204);
    }}