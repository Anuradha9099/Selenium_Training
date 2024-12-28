package com.temp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
    private final WebDriver webDriver;

    @FindBy(css = "span.title")
    WebElement titleLanding;

    public LandingPage(WebDriver webDriver){
        this.webDriver=webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public String getPageTitle() {
        return titleLanding.getText();
    }
}
