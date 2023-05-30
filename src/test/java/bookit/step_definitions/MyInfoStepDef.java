package bookit.step_definitions;

import bookit.pages.SelfPage;
import bookit.pages.SignInPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import utilities.BookitBrowserUtils;
import utilities.BookitConfReader;
import utilities.BookitDriver;

public class MyInfoStepDef {
    @Given("user logs in using {string} {string}")
    public void user_logs_in_using(String email, String password) {
        BookitDriver.get().get(BookitConfReader.get("url"));
        BookitDriver.get().manage().window().maximize();
        SignInPage signInPage = new SignInPage();
        signInPage.email.sendKeys(email);
        signInPage.password.sendKeys(password);
        BookitBrowserUtils.waitFor(1);
        signInPage.signInButton.click();



    }

    @When("user is on the my self page")
    public void user_is_on_the_my_self_page() {
        SelfPage selfPage = new SelfPage();
        selfPage.goToSelf();

    }


}
