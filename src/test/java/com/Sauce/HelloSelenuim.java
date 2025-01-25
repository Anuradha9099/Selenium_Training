package com.Sauce;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HelloSelenuim {

    //psvm- load the java main function - key word

    // TestNG is a testing framework for java that helps in automating tests

    private static final Logger logger = LogManager.getLogger(HelloSelenuim.class);

    @Test // functional test case need to be organized manner
    public void testHelloSelenuim(){
        try {
            logger.info("Starting the test");
            // Create a browser instance
            WebDriver webDriver= new ChromeDriver();

            //Navigate to source demo site
            webDriver.get("https://www.saucedemo.com");

            // Type Username
            webDriver.findElement(By.id("user-name")).sendKeys("standard_user");

            // Type Password
            webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
            //webDriver.findElement(By.id("Password")).sendKeys((Keys.ENTER));


            //Click login
            webDriver.findElement(By.id("login-button")).click();

            //Verify if the user can login to the system
            Assert.assertEquals(webDriver.findElement(By.cssSelector("span[data-test='title']")).getText(),"Products");

            // Close the browser
            //webDriver.quit();
            logger.info("Test Passed");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
