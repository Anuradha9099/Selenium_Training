package com.datadriven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SauceLoginDataDrivenTest {

    private final String BASE_URL;
    private WebDriver webDriver;

    public SauceLoginDataDrivenTest() {
        BASE_URL = "http://saucedemo.com";
    }

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get(BASE_URL);
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.close();
    }

    @DataProvider(name = "userCredentials")
    public static Object[][] userCredentials() {
        return new Object[][]{
                {"", "", "Epic sadface: Username is required"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"standard_user", "invalidPWD", "Epic sadface: Username and password do not match any user in this service"},
        };
    }

    @Test(dataProvider = "userCredentials")
    public void testLoginWithInvalidCredentials(String username, String password, String expectedError) {
        webDriver.findElement(By.id("user-name")).sendKeys(username);
        webDriver.findElement(By.id("password")).sendKeys(password);
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText(), expectedError, "Expected error is incorrect!");

    }

    @Test(dataProvider = "user-data", dataProviderClass = LoginInputClass.class)
    public void testLoginWithInvalidCredentialsData(String username, String password, String expectedError) {
        webDriver.findElement(By.id("user-name")).sendKeys(username);
        webDriver.findElement(By.id("password")).sendKeys(password);
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText(), expectedError);

    }
}