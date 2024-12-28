package com.pragmatic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class ExplicitWaitTest {

    WebDriver webDriver;

    @AfterClass
    public void afterClass() {
        webDriver.quit();
    }

    @Test
    public void testExplicitWaitTest() {

        webDriver=new ChromeDriver();
        webDriver.get("https://eviltester.github.io/synchole/collapseable.html");

        webDriver.findElement(By.cssSelector("#collapsable")).click();

        WebDriverWait wait=new WebDriverWait(webDriver,Duration.ofSeconds(10),Duration.ofMillis(50));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#aboutlink")));

        webDriver.findElement(By.cssSelector("#aboutlink")).click();
        webDriver.close();
    }

    @Test
    public void testExplicitWaitForButtonsTest() {

        webDriver=new ChromeDriver();
        webDriver.get("https://eviltester.github.io/synchole/buttons.html");

//        WebDriverWait wait=new WebDriverWait(webDriver,Duration.ofSeconds(10),Duration.ofMillis(50));
//
//        wait.until(ExpectedConditions.elementToBeClickable(By.id("button00")));
//        webDriver.findElement(By.id("button00")).click();
//
//        wait.until(ExpectedConditions.elementToBeClickable(By.id("button01")));
//        webDriver.findElement(By.id("button01")).click();
//
//        wait.until(ExpectedConditions.elementToBeClickable(By.id("button02")));
//        webDriver.findElement(By.id("button02")).click();
//
//        wait.until(ExpectedConditions.elementToBeClickable(By.id("button03")));
//        webDriver.findElement(By.id("button03")).click();

        waitAndClick(By.id("button00"));
        waitAndClick(By.id("button01"));
        waitAndClick(By.id("button02"));
        waitAndClick(By.id("button03"));
        Assert.assertEquals(webDriver.findElement(By.id("buttonmessage")).getText(),
                "All Buttons Clicked");
    }

    private void waitAndClick(By by) {
        WebDriverWait wait=new WebDriverWait(webDriver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(by));
        webDriver.findElement(by).click();
    }
}
