package com.selwebform;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckBoxTest {

    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();

        // Navigate to the Selenium web form page
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testCheckboxes() {
        // Locate the first checkbox
        WebElement firstCheckbox = driver.findElement(By.id("my-check-1"));

        // Assert that the first checkbox is selected
        Assert.assertTrue(firstCheckbox.isSelected(), "The first checkbox should be selected.");

        // Locate the second checkbox
        WebElement secondCheckbox = driver.findElement(By.id("my-check-2"));

        // Assert that the second checkbox is not selected
        Assert.assertFalse(secondCheckbox.isSelected(), "The second checkbox should not be selected by default.");

        // Click the second checkbox to select it
        secondCheckbox.click();

        // Assert that the second checkbox is now selected
        Assert.assertTrue(secondCheckbox.isSelected(), "The second checkbox should be selected after clicking.");
    }

}
