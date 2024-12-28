package com.pragmatic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class CrossBrowserTest {

    @Test
    public void testGoogleChrome() {

        //remove the disable Browser Popup
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        WebDriver webDriver=new ChromeDriver(options);

        // Create a map to disable password saving and autofill
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);  // Disable password manager
        prefs.put("profile.password_manager_enabled", false); // Disable password manager popup

        // Add preferences to ChromeOptions
        options.setExperimentalOption("prefs", prefs);

        webDriver.get("https://www.saucedemo.com");
        Assert.assertEquals(webDriver.getTitle(), "Swag Labs");

        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span[data-test='title']")).getText(),
                "Products");
    }

    //headless mode - doesn't open the browser
    //Headless browser automation uses a web browser for end-to-end testing without loading the browser’s UI.
    @Test
    public void testGoogleChromeHeadless() {
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--headless");

        WebDriver webDriver=new ChromeDriver(options);
        webDriver.get("https://www.saucedemo.com");
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span[data-test='title']")).getText(),
                "Products");
    }

    @Test
    public void testEdgeBrowser() {
        WebDriver webDriver=new EdgeDriver();
        webDriver.get("https://www.saucedemo.com");
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span[data-test='title']")).getText(),
                "Products");
    }
}
