package com.pragmatic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginOrangeHrmTest {
    WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod(){
        webDriver=new ChromeDriver();
        webDriver.get("https://opensource-demo.orangehrmlive.com");
    }

    @AfterMethod
    public void afterMethod(){
        try {
            // Pause for 5 seconds (5000 milliseconds)
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // Handle the exception
            System.out.println("Thread was interrupted: " + e.getMessage());
        }
        webDriver.quit();
    }

    @Test
    public void testLoginWithValidCredentails(){

        webDriver.findElement(By.cssSelector("input[name='Username']")).sendKeys("Admin");
        webDriver.findElement(By.cssSelector("input[name='Password']")).sendKeys("admin123");
        webDriver.findElement(By.cssSelector("button[type='submit']")).click();

        //Verify if the user can login to the system
        Assert.assertEquals(webDriver.findElement(By.cssSelector(".oxd-text.oxd-text--h6.oxd-topbar-header-breadcrumb-module")).getText(),
                "Dashboard");
    }

    @Test
    public void testLoginWithBlankUsernameAndBlankPassword(){
        webDriver.findElement(By.cssSelector("input[name='Username']")).clear();
        webDriver.findElement(By.cssSelector("input[name='Password']")).clear();
        webDriver.findElement(By.cssSelector("button[type='submit']")).click();

        Assert.assertEquals(webDriver.findElement(By.xpath("//div[@class='orangehrm-login-slot-wrapper']//div[1]//div[1]//span[1]")).getText(),
                "required");
        Assert.assertEquals(webDriver.findElement(By.xpath("//div[@class='orangehrm-login-form']//div[2]//div[1]//span[1]")).getText(),
                "required");
    }

    @Test
    public void testLoginWithInvalidUsernameAnPassword(){
        webDriver.findElement(By.cssSelector("input[name='Username']")).sendKeys("Admin1");
        webDriver.findElement(By.cssSelector("input[name='Password']")).sendKeys("admin12345");
        webDriver.findElement(By.cssSelector("button[type='submit']")).click();

        Assert.assertEquals(webDriver.findElement(By.cssSelector(".oxd-text.oxd-text--p.oxd-alert-content-text")).getText(),
                "Invalid credentials");
    }
}
