package com.temp.tests;

import com.temp.SauceDemoTestBase;
import com.temp.pages.LandingPage;
import com.temp.pages.SauceLoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/*
How It Works:
The test classes (e.g., SauceDemoLoginTest) rely on the methods in pages to
perform actions on the application.

Example:
Instead of locating elements directly in the test file, the test calls the typeUsername, typePassword, and clickLogin methods from the SauceLoginPage class.

Benefit:
Separates test logic from UI logic, making tests more readable and maintainable.
 */

public class SauceDemoLoginTest extends SauceDemoTestBase {

    @Test
    public void testLoginWithDefaultUserCredentials() {

        SauceLoginPage loginPage=new SauceLoginPage(webDriver);
        loginPage.typeUsername("standard_user").typePassword("secret_sauce").clickLogin();

        LandingPage landingPage=new LandingPage(webDriver);

        // assertj assertion
        assertThat(landingPage.getPageTitle())
                    .isEqualTo("Products")
                    .as("Page title is not as expected")
                    .contains("Products")
                    .startsWith("Pro")
                    .endsWith("cts")
                    .doesNotContain("Login")
                    .isNotBlank()
                    .isNotEmpty()
                    .isNotNull();
    }

    @Test
    public void testLoginWithBlankCredentials() {
        SauceLoginPage loginPage=new SauceLoginPage(webDriver);
        loginPage.clearUsername().clearPassword().clickLogin();
        Assert.assertEquals(loginPage.getErrorMessage(),"Epic sadface: Username is required");
    }
}
