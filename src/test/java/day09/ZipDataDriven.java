package day09;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class ZipDataDriven {
    @ParameterizedTest
   @CsvFileSource(resources="/state_city.csv", numLinesToSkip=1)
    public void testStateCity(String expectedState,String expectedCity,int numberOfZipcodes){

      //  System.out.println("state = " + state);
       // System.out.println("city = " + city);
       // System.out.println("numberOfZipcodes = " + numberOfZipcodes);
       Response response= given()
                    .pathParam("state",expectedState)
                    .pathParam("city",expectedCity)
                    .baseUri("http://api.zippopotam.us/us").
             when()
                     .get("/{state}/{city}")
                     .prettyPeek() ;
                    // .get("/{state}/{city}" , state,city)
        //assert that city and state match in the response
        JsonPath jp=response.jsonPath();
        // why do we use single quote? because there can not be a space in json path
        // we are using the '' to treat the 2 word as one
        System.out.println("state = "+jp.getString("'state abbreviation'"));
        System.out.println("city ="+jp.getString("'place name'"));

        // asserting actual result from response to expected result from csv file
        // asserting actual result from response to expected result from csv file
        assertThat( jp.getString("'state abbreviation'"), is(expectedState) );
        assertThat( jp.getString("'place name'"), is(expectedCity) );
        // now we want to count how many item in jsonArray from the respons e
        // and validate that against our csv file expected numbers
        // since post code key has space in between we have to add '' to treat it as one
        List<String> zipList=jp.getList("places.'post code'");
        System.out.println("zipList = " + zipList);

        assertThat(zipList,hasSize(numberOfZipcodes));

        // OPTIONALLY YOU MAY DO AS BELOW TO COUNT YOUR JSON ARRAY
        // if your jsonpath is pointing to an jsonArray you can count them
        // by called groovy method called size()
        System.out.println("calling the size method directly in jsonPath = "
                + jp.getInt("places.size()"));


    }



}
