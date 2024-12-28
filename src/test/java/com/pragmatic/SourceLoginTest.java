package com.pragmatic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SourceLoginTest {

    WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod(){
        webDriver=new ChromeDriver();
        webDriver.get("https://www.saucedemo.com");
    }

    @AfterMethod
    public void afterMethod(){
        webDriver.quit();
    }

    @Test
    public void testLoginWithValidMultipleUsernames() {

        String[] usernames = {"standard_user","locked_out_user","problem_user","performance_glitch_user","error_user","visual_user"};
        String password ="secret_sauce";

        for (String username : usernames){

            webDriver.findElement(By.id("user-name")).sendKeys(username);
            webDriver.findElement(By.id("password")).clear();
            webDriver.findElement(By.id("password")).sendKeys(password);

            if (username.equals("locked_out_user")){
                webDriver.findElement(By.id("login-button")).click();
                Assert.assertEquals(webDriver.findElement(By.xpath("//h3[contains(text(),'Epic')]")).getText(),
                        "Epic sadface: Sorry, this user has been locked out.");
                webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                webDriver.findElement(By.id("user-name")).clear();
                webDriver.findElement(By.id("password")).clear();
            }
            else
            {
                webDriver.findElement(By.id("login-button")).click();
                webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                //Verify if the user can login to the system
                Assert.assertEquals(webDriver.findElement(By.cssSelector("span[data-test='title']")).getText(),
                        "Products");

                webDriver.findElement(By.cssSelector("#react-burger-menu-btn")).click();
                webDriver.findElement(By.cssSelector(("#logout_sidebar_link"))).click();
            }
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        }
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
