package bookit.step_definitions;

import bookit.pages.SelfPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.BookitConfReader;
import utilities.BookitUtil;
import utilities.DBUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiStepDefinition {
    String token;
    Response response;
    String emailGlobal;
int idToDelete;
    @Given("I logged Bookit api using {string} and {string}")
    public void i_logged_Bookit_api_using_and(String email, String password) {

        token = BookitUtil.generateToken(email, password);

    }

    @When("I get the current user information from api")
    public void i_get_the_current_user_information_from_api() {
        response = given()
                .accept(ContentType.JSON)
                .and()
                .header("Authoriazation", token)
                .when().get(BookitConfReader.get("base_url") + "/api/users/me");
        response.prettyPrint();

    }

    @Then("status code should be {int}")
    public void status_code_should_be(int statusCode) {
        //get the status code from global response, which is stored from previous request
        //and verify if it matches with the status code from feature file
        System.out.println(response.statusCode());
        Assert.assertEquals(statusCode, response.statusCode());


    }

    @Then("the information about current user from api and database should match")
    public void theInformationAboutCurrentUserFromApiAndDatabaseShouldMatch() {


        //get information from database
        String query = "SELECT firstname,lastname,role\n" +
                "FROM users\n" +
                "WHERE email = '" + emailGlobal + "'";

        Map<String, Object> dbMap = DBUtils.getRowMap(query);
        System.out.println(dbMap);

        //save database information into expected variables
        String expectedFirstName = (String) dbMap.get("firstname");
        String expectedLastName = (String) dbMap.get("lastname");
        String expectedRole = (String) dbMap.get("role");


        //get information from api
        JsonPath jsonPath = response.jsonPath();

        String actualFirstName = jsonPath.getString("firstName");
        String actualLastName = jsonPath.getString("lastName");
        String actualRole = jsonPath.getString("role");

        //compare database vs api

        Assert.assertEquals(expectedFirstName, actualFirstName);
        Assert.assertEquals(expectedLastName, actualLastName);
        Assert.assertEquals(expectedRole, actualRole);
    }

    @Then("UI,API and Database user information must be match")
    public void uiAPIAndDatabaseUserInformationMustBeMatch() {

        String query = "SELECT firstname,lastname,role\n" +
                "FROM users\n" +
                "WHERE email = '" + emailGlobal + "'";

        Map<String, Object> dbMap = DBUtils.getRowMap(query);
        System.out.println(dbMap);

        //save database information into expected variables
        String expectedFirstName = (String) dbMap.get("firstname");
        String expectedLastName = (String) dbMap.get("lastname");
        String expectedRole = (String) dbMap.get("role");


        //get information from api
        JsonPath jsonPath = response.jsonPath();

        String actualFirstName = jsonPath.getString("firstName");
        String actualLastName = jsonPath.getString("lastName");
        String actualRole = jsonPath.getString("role");

        //compare database vs api

        Assert.assertEquals(expectedFirstName, actualFirstName);
        Assert.assertEquals(expectedLastName, actualLastName);
        Assert.assertEquals(expectedRole, actualRole);

        //getting infromation from UI
        SelfPage selfPage = new SelfPage();

        String acctualFullNameUI = selfPage.name.getText();
        String actualRoleFromUI = selfPage.role.getText();

        System.out.println(acctualFullNameUI);
        System.out.println(actualRoleFromUI);

        //UI vs APi
        //create one api fillName variable
        String actualFullName = actualFirstName + " " + actualLastName;
        Assert.assertEquals(actualFullName, acctualFullNameUI);
        Assert.assertEquals(actualRole, actualRoleFromUI);


    }

    @When("I send POST request {string} endpoint with following information")
    public void iSendPOSTRequestEndpointWithFollowingInformation(String path,
                                                                 Map<String, String> userInfo) {
   response= given()
            .accept(ContentType.JSON)
            .and()
            .header("Authorization",token)
            .queryParams(userInfo)
            .log().all()
            .when()
            .post(BookitConfReader.get("base_url")+path)
            .then()
            .log().all().extract().response();
  idToDelete= response.path("entryiId");

    }


    @And("I delete previously added student")
    public void iDeletePreviouslyAddedStudent() {
        //we need id from previous request

        given()
                .header("Authorization",token)
                .pathParam("id",idToDelete)
                .when()
                .delete(BookitConfReader.get("base_url")+"api/students/{id}")
                .then().statusCode(204);
    }
}


