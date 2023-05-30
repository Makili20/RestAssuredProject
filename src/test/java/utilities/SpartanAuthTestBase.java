package utilities;


import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class SpartanAuthTestBase {

    //beforeAll is the same thing with beforeClass in testng
    @BeforeAll
    public static void init(){
        RestAssured.baseURI ="http://3.84.25.6:7000";
        RestAssured.basePath="/api";

      /*  String dbUrl ="jdbc:oracle:thin:@3.84.25.6:1521:XE";
        String dbUsername ="SP";
        String dbPassword = "SP";


       */
        //DBUtils.createConnection(dbUrl,dbUsername,dbPassword);
    }

    @AfterAll
    public static void close(){
        RestAssured.reset();
        // DBUtils.destroy();
    }
}