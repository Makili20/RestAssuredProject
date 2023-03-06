package day01;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Practice1 {
    // http://44.199.212.141:8000/api.hello

    @Test
    public void test1() {
        //make sure your request works in postman
        //if anything does not work manually it will not work in here either
        //  RestAssured.get("URL here");
        //SINCE WE DID THE STATIC IMPORT
        //WE CAN DIRECTLY CALL THE GET METHOD
        //after we send the rew=quest
        //we can save the result into a type called Response

        Response response = get("http://44.199.212.141:8000/api/hello");
        System.out.println("status code of this response: " + response.statusCode());
        System.out.println("getting status Line of this response: "+response.statusLine());


        //in Rest ASsured there are usually 2 methods that does some action
        //one directly with the name like response.statusCode
        //another starting with getXXX like response.getStatusCode()

        System.out.println("Getting the value of the header : "+response.header("Date"));
        System.out.println("Getting Content-Type header value: "+response.contentType());
        System.out.println("Getting Content-Length header value: "+response.header("Content-Length"));
        System.out.println("Getting Connection header value: "+response.header("Connection"));


    }
    @DisplayName("testing/hello endpoint")
    @Test
    public void testHello(){
        Response response=get("http://44.199.212.141:8000/api/hello");
        //testing the status code
        assertEquals(200,response.statusCode());
        //testing the Content-Type header value is: text/plain;charset=UTF-8
        assertEquals("text/plain;charset=UTF-8",response.contentType());
        //testing the Content-Length value is: 17
        assertEquals("17",response.header("Content-Length"));

    }
    @DisplayName("Testing/hello endpoint body")
    @Test
    public void testingHelloResponseBody(){
        //get the body and assert the body equal to Hello from Sparta
        Response response=get("http://44.199.212.141:8000/api/hello");
        //getting the body as STring using asString ,method
        System.out.println(response.asString());
        //getting the body by calling body method
        System.out.println(response.body().asString());
        //assert the body is Hello from Sparta,length is 17
        String helloBody=response.asString();

      assertEquals("Hello from Sparta", helloBody);
      assertEquals(17,helloBody.length());

    }

    @DisplayName("Printing the response body using method")
    @Test
    public void printingBody(){
        Response response=get("http://44.199.212.141:8000/api/hello");
        response.prettyPrint();
        System.out.println("==========================");
        //it will print out the entire response
        response.prettyPeek();
        System.out.println("================");
        //I want to see entire repsonse plus to see the status code into a variable in same
        //statement
       int statusCode= response.prettyPeek().statusCode();
        System.out.println("PRINTING ONLY STATUS CODE "+statusCode);


    }




}
