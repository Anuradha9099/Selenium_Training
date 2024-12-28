package com.selwebform;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CheckEachWebElementsTest {

    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();

        // Navigate to the Selenium web form page
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
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

    @Test
    public void testSelectSpecificDateInCurrentDate()  {
        LocalDate currentDate = LocalDate.now();
        int currentDay = 14; // Fixed day of the current month
        String expectedDate = currentDate.withDayOfMonth(currentDay).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        System.out.println(expectedDate);

        WebElement dateInput = driver.findElement(By.name("my-date"));
        dateInput.click();
        driver.findElement(By.xpath("//td[@class='day' and text()='14']")).click();
        Assert.assertEquals(dateInput.getDomProperty("value"), expectedDate);
    }

    @Test
    public void testNavigateThroughMonths()  {

        //select current month
        WebElement datePicker = driver.findElement(By.xpath("//input[@name='my-date']"));
        datePicker.click();

        //select next month
        WebElement nextMonthButton = driver.findElement(By.xpath("//div[@class='datepicker-days']//th[@class='next'][normalize-space()='»']"));
        nextMonthButton.click();

        WebElement displayedMonth = driver.findElement(By.xpath("//th[normalize-space()='January 2025']")); // Adjust
        Assert.assertEquals(displayedMonth.getText(), "January 2025");
    }

    @Test
    public void testInputDateIsVisibleInCalendar()  {
        WebElement datePicker = driver.findElement(By.xpath("//input[@name='my-date']"));
        datePicker.sendKeys("01/14/2001");
        String displayed_date = datePicker.getDomProperty("value");
        Assert.assertEquals(displayed_date, "01/14/2001", "The displayed date does not match the input date.");
    }
}
