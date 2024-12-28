package com.pragmatic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BasicAuthTest {

    private WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod(){
        webDriver=new ChromeDriver();
        //webDriver.get("https://the-internet.herokuapp.com/basic_auth");
    }

    @AfterMethod
    public void afterMethod(){
        webDriver.quit();
    }

    @Test
    public void testBasicAuth() {

        String username="admin";
        String password="admin";
        String BASE_URL="https://the-internet.herokuapp.com/basic_auth";

        String authenticatedURL="https://"+username+":"+password+"@"+ BASE_URL.replace("https://","");
        String authenticatedURL1= String.format("https://%s:%s@the-internet.herokuapp.com/basic_auth",username,password);

        webDriver.get(authenticatedURL1);

        //validate successful login by checking the page text
        String pageSource=webDriver.getPageSource();

        Assert.assertTrue(pageSource.contains("Congratulations! You must have the proper credentials."),
                "Login failed: Expected success message not found!");

        Assert.assertEquals(webDriver.findElement(By.tagName("h3")).getText(),
                "Basic Auth");

        Assert.assertEquals(webDriver.findElement(By.tagName("p")).getText(),
                "Congratulations! You must have the proper credentials.");



    }
}
