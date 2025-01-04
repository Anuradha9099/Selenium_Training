package com.Sauce;

import net.datafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CheckOutFlowTest {

    WebDriver webDriver;
    //private final static BASE_URL='https://www.saucedemo.com';

    @BeforeClass
    public void beforeClass() {
        webDriver=new ChromeDriver();
        //webDriver.navigate().forward();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.saucedemo.com");
    }

    @AfterClass
    public void afterClass() {
//        if(webDriver!=null){
//            webDriver.quit();
//        }
        try {
            // Pause for 5 seconds (5000 milliseconds)
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // Handle the exception
            System.out.println("Thread was interrupted: " + e.getMessage());
        }
        webDriver.quit();
    }

    @Test
    public void testCheckoutFlowTest() {
        Faker faker=new Faker();
        String firstName=faker.name().firstName();
        String lasttName=faker.name().lastName();

        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();

        //Verify if the user can login to the system
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span[data-test='title']")).getText(),
                "Products");

        webDriver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        webDriver.findElement(By.cssSelector(".shopping_cart_link")).click();
        webDriver.findElement(By.id("checkout")).click();
        webDriver.findElement(By.id("first-name")).sendKeys(firstName);
        webDriver.findElement(By.id("last-name")).sendKeys(lasttName);
        webDriver.findElement(By.id("postal-code")).sendKeys("47017");
        webDriver.findElement(By.id("continue")).click();
        webDriver.findElement(By.id("finish")).click();

        Assert.assertEquals(webDriver.findElement(By.xpath("//h2[@data-test='complete-header']")).getText(),
                "Thank you for your order!");

    }
}
