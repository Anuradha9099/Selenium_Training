package com.selwebform;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CopyAndPasteTest {

    @Test
    public void testCopyAndPaste() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement textField = driver.findElement(By.id("my-text-id"));
        textField.sendKeys("Selenium!");

        // Create an Actions instance
        Actions actions = new Actions(driver);

        // Select the entire text in the textbox
        actions.click(textField) // Focus on the textbox
                .keyDown(Keys.CONTROL)
                .sendKeys("a") // Select all text
                .keyUp(Keys.CONTROL)
                .perform();

        // Copy the selected text
        actions.keyDown(Keys.CONTROL)
                .sendKeys("c") // Copy text
                .keyUp(Keys.CONTROL)
                .perform();

        // Move the cursor to the end of the text
        textField.sendKeys(Keys.END);

        // Paste the copied text at the end of the same textbox
        actions.keyDown(Keys.CONTROL)
                .sendKeys("v") // Paste text
                .keyUp(Keys.CONTROL)
                .perform();

        Assert.assertEquals( textField.getDomProperty("value"), "Selenium!Selenium!");
    }
}
