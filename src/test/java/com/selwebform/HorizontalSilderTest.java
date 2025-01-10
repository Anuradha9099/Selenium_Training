package com.selwebform;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class HorizontalSilderTest {

    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        // Initialize WebDriver (driver setup is assumed to be handled externally)
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/horizontal_slider");
    }

    @AfterMethod
    public void afterMethod() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testHorizontalSlider() {

        // Locate the slider element
        WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));

        // Locate the value display element (next to the slider)
        WebElement valueDisplay = driver.findElement(By.xpath("//span[@id='range']")); // Adjust locator for the displayed value

        // Create an Actions instance
        Actions actions = new Actions(driver);

        // Move the slider (drag and drop or using arrow keys)
        actions.clickAndHold(slider).moveByOffset(50, 0).release().perform(); // Adjust offset for desired value

        // Set up an explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds wait
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//span[@id='range']"))));

        // Assert the new value
        Assert.assertEquals(valueDisplay.getText(), "4.5", "Slider value did not match expected value.");
    }
}
