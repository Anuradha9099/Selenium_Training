package com.pragmatic;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JavaScriptPopupTest {

    private WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod(){
        webDriver=new ChromeDriver();
        webDriver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @AfterMethod
    public void afterMethod(){
        webDriver.quit();
    }

    @Test
    public void testAlert() {
        webDriver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        Alert jsAlert= webDriver.switchTo().alert();

        //check the alert message
        Assert.assertEquals(jsAlert.getText(),"I am a JS Alert");

        // click OK button and check the message
        jsAlert.accept();

        Assert.assertEquals(webDriver.findElement(By.id("result")).getText().trim(),"You successfully clicked an alert");
    }

    @Test
    public void testAlertConfirmationOK() {
        webDriver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        Alert jsAlert= webDriver.switchTo().alert();

        //check the alert message
        Assert.assertEquals(jsAlert.getText(),"I am a JS Confirm");
        jsAlert.accept();
        Assert.assertEquals(webDriver.findElement(By.id("result")).getText().trim(),"You clicked: Ok");
    }

    @Test
    public void testAlertPrompt() {
        webDriver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        Alert jsPrompt= webDriver.switchTo().alert(); //open the alert box

        //check the alert message
        Assert.assertEquals(jsPrompt.getText(),"I am a JS prompt");

        String textToEnter="Selenium";
        jsPrompt.sendKeys(textToEnter);
        jsPrompt.accept();
        Assert.assertEquals(webDriver.findElement(By.id("result")).getText().trim(),String.format("You entered: %s",textToEnter));
    }


}
