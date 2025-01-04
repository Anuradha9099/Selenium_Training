package com.selwebform;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DropDownListSelecorTest {

    WebDriver webDriver;

    @BeforeClass
    public void beforeClass() {
        webDriver = new ChromeDriver();
        webDriver.get("https://www.selenium.dev/selenium/web/web-form.html");
    }

    @AfterClass
    public void afterClass() {
        webDriver.quit();
    }

    @Test
    public void testDropdownSelect() {

        Select selNumber = new Select(webDriver.findElement(By.name("my-select")));
        selNumber.selectByIndex(2);
        Assert.assertEquals(selNumber.getFirstSelectedOption().getText(), "Two");
        selNumber.selectByVisibleText("One");
        selNumber.selectByValue("3");
        Assert.assertEquals(selNumber.getFirstSelectedOption().getText(), "Three");
    }

    @Test
    public void testDropdownDataList() {

        // Locate the input field associated with the datalist
        WebElement datalistInput = webDriver.findElement(By.name("my-datalist"));

        // Send the desired value to the input field
        datalistInput.sendKeys("San Francisco");

        // Get the value entered in the input field
        String enteredValue = datalistInput.getDomProperty("value");

        // Validate that the value was correctly entered
        Assert.assertEquals(enteredValue, "San Francisco", "The entered value is incorrect!");
    }
}
