package com.dataprovider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenTest {

    private final String BASE_URL;
    private WebDriver driver;

    public DataDrivenTest() {
        BASE_URL = "http://saucedemo.com";
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void afterMethod() {
        driver.close();
    }

    @Test(description = "CSV Data test", priority = 1, dataProvider = "csvUserCredentials", dataProviderClass = TestData.class)
    public void testLoginWithInvalidCredentialsDataInCSV(String username, String password, String expectedErrorMessage) {
        performLogin(username, password);
        Assert.assertEquals(driver.findElement(By.cssSelector("h3[data-test='error']")).getText(), expectedErrorMessage);
    }

    @Test(description = "JSON Data test", priority = 2, dataProvider = "jsonUserCredentials", dataProviderClass = TestData.class)
    public void testLoginWithInvalidCredentialsDataInJSON(String username, String password, String expectedErrorMessage) {
        performLogin(username, password);
        Assert.assertEquals(driver.findElement(By.cssSelector("h3[data-test='error']")).getText(), expectedErrorMessage);
    }

    @Test(description = "XML Data test", priority = 3, dataProvider = "xmlUserCredentials", dataProviderClass = TestData.class)
    public void testLoginWithInvalidCredentialsDataInXML(String username, String password, String expectedErrorMessage) {
        performLogin(username, password);
        Assert.assertEquals(driver.findElement(By.cssSelector("h3[data-test='error']")).getText(), expectedErrorMessage);
    }

    private void performLogin(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }
}