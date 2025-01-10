package com.selwebform;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
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
    public void testSelectSpecificDateInCurrentDate() {
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
    public void testNavigateThroughMonths() {

        //The date picker test needs to dynamically handle the transition to the next month
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement datePicker = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='my-date']")));
        datePicker.click();

        //select next month button
        WebElement nextMonthButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='datepicker-days']//th[@class='next'][normalize-space()='»']")));
        nextMonthButton.click();

        //Get the next month dynamically
        LocalDate currentDate = LocalDate.now();
        LocalDate nextMonthDate = currentDate.plusMonths(1); //get the date for next month
        String nextMonth = nextMonthDate.getMonth().name(); // get the next month name in all uppercase (eg. FEBRUARY)

        int nextYear = nextMonthDate.getYear(); //get the year of the next month (accounting for december -> january transition)

        //format the expected month and year in camel case
        String expectedMonthText = toCamelCase(nextMonth) + " " + nextYear;
        System.out.println(expectedMonthText);

        //verify the displayed month (both expected and actual in lowercase for comparison)
        WebElement displayedMonth = driver.findElement(By.xpath("//th[normalize-space(text())='" + expectedMonthText + "']"));

        Assert.assertEquals(displayedMonth.getText(), expectedMonthText, "Displayed month is incorrect.");
    }

    //helper method to convert string to camelcase
    private String toCamelCase(String name) {
        String convertedName = name.toLowerCase();
        if (convertedName.isEmpty()) {
            return convertedName;
        }
        return convertedName.substring(0, 1).toUpperCase() + convertedName.substring(1);
    }


    @Test
    public void testInputDateIsVisibleInCalendar() {
        WebElement datePicker = driver.findElement(By.xpath("//input[@name='my-date']"));
        datePicker.sendKeys("01/14/2001");
        String displayed_date = datePicker.getDomProperty("value");
        Assert.assertEquals(displayed_date, "01/14/2001", "The displayed date does not match the input date.");
    }
}
