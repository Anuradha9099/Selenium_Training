package com.Locators;

import com.datadriven.LoginInputClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SauceLoginElementLocator {

    private final String BASE_URL;
    private WebDriver webDriver;

    public SauceLoginElementLocator() {

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

    @Test
    public void testLoginWithValidCredentails(){

        /*
        Find Element: The method creates a custom By object that locates elements with placeholder="Username".
         */
        webDriver.findElement(new ByAttributeAndValue("placeholder","Username")).sendKeys("standard_user");

        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span[data-test='title']")).getText(),
                "Products");
    }

    @Test
    public void testLoginWithBlankUsernameAndBlankPassword(){
        webDriver.findElement(By.id("user-name")).clear();
        webDriver.findElement(By.id("password")).clear();
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText(),
                "Epic sadface: Username is required");
    }

    @Test
    public void testLoginWithValidUsernameAndBlankPassword(){
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).clear();
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText(),
                "Epic sadface: Password is required");
    }

    @Test
    public void testLoginWithBlankUsernameAndValidPassword(){
        webDriver.findElement(By.id("user-name")).clear();
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText(),
                "Epic sadface: Username is required");
    }

    @Test
    public void testLoginWithInvalidUsernameAndPassword(){
        webDriver.findElement(By.id("user-name")).sendKeys("test1");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce1");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText(),
                "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void testLoginWithInvalidUsernameAndBlankPassword(){
        webDriver.findElement(By.id("user-name")).sendKeys("test1");
        webDriver.findElement(By.id("password")).clear();
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText(),
                "Epic sadface: Username is required");
    }

    @Test
    public void testLoginWithBlankUsernameAndInvalidPassword(){
        webDriver.findElement(By.id("user-name")).clear();
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce1");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText(),
                "Epic sadface: Username is required");
    }
}
