package com.pragmatic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ImplicitWaitTest {

    @Test
    public void testButtonClickWithImplicitWaits() {

        WebDriver webDriver=new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //maximum it will wait 10 seconds after that if the time is exceed then time exception will occur
        webDriver.get("https://eviltester.github.io/synchole/buttons.html");

        webDriver.findElement(By.id("easy00")).click();
        webDriver.findElement(By.id("easy01")).click();
        webDriver.findElement(By.id("easy02")).click();
        webDriver.findElement(By.id("easy03")).click();

        Assert.assertEquals(webDriver.findElement(By.id("easybuttonmessage")).getText(),
                "All Buttons Clicked");
        //as a best practice, when define the implicit wait then set seconds to 0 to minimized the unexpected behaviours
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        webDriver.close();
    }
}
