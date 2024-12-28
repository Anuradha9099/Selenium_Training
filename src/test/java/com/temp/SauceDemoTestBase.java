package com.temp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class SauceDemoTestBase {

    public WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod(){
        webDriver=new ChromeDriver();
        webDriver.get("https://www.saucedemo.com");
    }

    @AfterMethod
    public void afterMethod(){
        webDriver.quit();
    }
}
