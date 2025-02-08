package com.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class SauceLoginSteps {

    private WebDriver webDriver;

    @After
    public void after(Scenario scenario){
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot " + scenario.getName());
        }
        webDriver.close();
    }

    @Given("User has accessed to the saucedemo login page")
    public void userHasAccessedToTheSaucedemoLoginPage() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.saucedemo.com");
    }

    @When("User enters valid credentials")
    public void userEntersValidCredentials() {
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
    }

    @Then("User should be able to navigate to the landing page")
    public void userShouldBeAbleToNavigateToTheLandingPage() {
        assertThat(webDriver.findElement(By.cssSelector("span[data-test='title']")).getText())
                .isEqualTo("Products")
                .as("Page title is not as expected")
                .contains("Products")
                .startsWith("Pro")
                .endsWith("cts");
    }

    @When("User enters invalid credentials username {string} and password {string}")
    public void userEntersInvalidCredentialsUsernameAndPassword(String arg0, String arg1) {
        webDriver.findElement(By.id("user-name")).sendKeys(arg0);
        webDriver.findElement(By.id("password")).sendKeys(arg1);
        webDriver.findElement(By.id("login-button")).click();
    }

    @Then("User should see the error message {string}")
    public void userShouldSeeTheErrorMessage(String arg0) {
        assertThat(webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText())
                .isEqualTo(arg0);
    }
}
