package bookit.step_definitions;


import cucumber.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.BookitDBUtils;
import utilities.BookitDriver;

import java.util.concurrent.TimeUnit;

public class Hooks {

    @Before("@db")
    public void dbHook() {
        System.out.println("creating database connection");
        BookitDBUtils.createConnection();
    }

    @After("@db")
    public void afterDbHook() {
        System.out.println("closing database connection");
        BookitDBUtils.destroyConnection();

    }

    @Before
    public void setUp() {
        // we put a logic that should apply to every scenario
        BookitDriver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @After
    public void tearDown(Scenario scenario) {
        // only takes a screenshot if the scenario fails
        if (scenario.isFailed()) {
            // taking a screenshot
            final byte[] screenshot = ((TakesScreenshot) BookitDriver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
        BookitDriver.closeDriver();
    }
}
