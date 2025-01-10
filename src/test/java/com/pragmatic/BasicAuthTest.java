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
    private String username="admin";
    private String password="admin";
    private String BASE_URL="https://the-internet.herokuapp.com/basic_auth";

    @BeforeMethod
    public void beforeMethod(){
        webDriver=new ChromeDriver();
        //webDriver.get("https://the-internet.herokuapp.com/basic_auth");
    }

    @AfterMethod
    public void afterMethod(){
        webDriver.quit();
    }

    //utility method to construct the authenticated URLs
    public static String getAuthenticatedRL(String uname, String pwd, String url) {
        return String.format("https://%s:%s@%s",uname,pwd,url.replace("https://",""));
    }

    @Test
    public void testBasicAuth() {

//        String authenticatedURL="https://"+username+":"+password+"@"+ BASE_URL.replace("https://","");
//        String authenticatedURL1= String.format("https://%s:%s@the-internet.herokuapp.com/basic_auth",username,password);

        //utility method to construct the authenticated URLs
        webDriver.get(getAuthenticatedRL(username,password,BASE_URL));

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
