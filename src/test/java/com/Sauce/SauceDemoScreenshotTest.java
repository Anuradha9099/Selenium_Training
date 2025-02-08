package com.Sauce;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class SauceDemoScreenshotTest {
    private WebDriver webDriver;
    private static final String SAUCE_DEMO_URL = "https://www.saucedemo.com";
    private static final String SCREENSHOT_FOLDER = "./screenshots/";

    @BeforeClass
    public void setUp() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get(SAUCE_DEMO_URL);
    }

    @Test
    public void takeLoginFormScreenshot() throws IOException {
        WebElement loginForm = webDriver.findElement(By.id("login_button_container"));
        String filePath = takeElementScreenshot(loginForm, "login_form.png");
        Assert.assertTrue(new File(filePath).exists(), "Login form screenshot was not created.");
    }

    @Test
    public void takeFullPageScreenshot() throws IOException {
        String filePath = takeFullPageScreenshot("full_page.png");
        Assert.assertTrue(new File(filePath).exists(), "Full page screenshot was not created.");
    }

    private String takeElementScreenshot(WebElement element, String fileName) throws IOException {
        File scrFile = element.getScreenshotAs(OutputType.FILE);
        File destFile = new File(SCREENSHOT_FOLDER + fileName);
        FileUtils.copyFile(scrFile, destFile);
        System.out.println("Screenshot saved: " + destFile.getAbsolutePath());
        return destFile.getAbsolutePath();
    }

    private String takeFullPageScreenshot(String fileName) throws IOException {
        File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(SCREENSHOT_FOLDER + fileName);
        FileUtils.copyFile(scrFile, destFile);
        System.out.println("Full page screenshot saved: " + destFile.getAbsolutePath());
        return destFile.getAbsolutePath();
    }

    @AfterClass
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
