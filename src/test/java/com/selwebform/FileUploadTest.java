package com.selwebform;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

public class FileUploadTest {
    
    private WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod(){

        webDriver = new ChromeDriver();
        webDriver.get("https://the-internet.herokuapp.com/upload");
        webDriver.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod(){
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test(priority = 1)
    public void testSuccessFileUpload() {

        // Get the absolute path of the image located under resources/images
        String filePath = new File(System.getProperty("user.dir")+"/testData/username_data.xlsx").getAbsolutePath();
        // Send the file path to the file input field
        webDriver.findElement(By.id("file-upload")).sendKeys(filePath);
        webDriver.findElement(By.id("file-submit")).click();

        Assert.assertEquals(webDriver.findElement(By.cssSelector("#content h3")).getText(), "File Uploaded!");
    }

    @Test(priority = 2)
    public void testInvalidFileExtentionUpload() {

        webDriver.findElement(By.id("file-upload")).sendKeys("C:/Users/home/Downloads/apache-maven-3.9.9-bin");
        webDriver.findElement(By.id("file-submit")).click();

        String pageSource=webDriver.getPageSource();
        Assert.assertTrue(pageSource.contains("This site can’t be reached"),
                "File uploaded!");
    }

}
