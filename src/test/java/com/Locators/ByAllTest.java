package com.Locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ByAllTest {

    @Test
    public void testByAll() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.saucedemo.com");
        By byAllInputs = new ByAll(By.id("user-name"), By.cssSelector("[placeholder='Password']"));

        List<WebElement> eleInputs = webDriver.findElements(byAllInputs);
        Assert.assertEquals(eleInputs.size(), 2);

        eleInputs.get(0).sendKeys("standard_user");
        eleInputs.get(1).sendKeys("secret_sauce");

    }
}
