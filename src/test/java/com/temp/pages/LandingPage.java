package com.temp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LandingPage {
    private final WebDriver webDriver;

    @FindBy(css = "span.title")
    private WebElement titleLanding;

    public LandingPage(WebDriver webDriver){
        this.webDriver=webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public String getPageTitle() {

        try{
            WebDriverWait wait=new WebDriverWait(webDriver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(titleLanding)); // wait for visibility
            return titleLanding.getText().trim();
        } catch (Exception e) {
            e.printStackTrace(); //add better logging if needed
            return null;
        }
    }
}
