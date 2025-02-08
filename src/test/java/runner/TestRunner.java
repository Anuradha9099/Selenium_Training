package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/features/login.feature"},
        glue = {"com.cucumber"},
        plugin = {
                "pretty", // prints the gherkin steps in the console
                "html:target/cucumber-reports.html", // generates a html report
                "json:target/cucumber.json" // generates a json report
        },
        monochrome = true,
        tags = "@regression"
)

public class TestRunner extends AbstractTestNGCucumberTests {
    // NO CODE NEEDED HERE
}
