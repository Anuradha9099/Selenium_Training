package com.temp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*
How It Works:
Each class represents a single page in the application (e.g., SauceLoginPage represents the login page).
These classes encapsulate all the elements (WebElement) and actions (methods) specific to that page.

Example:
The SauceLoginPage class defines locators (txtUsername, txtPassword, btnLogin) and operations
(typeUsername, typePassword, clickLogin) for interacting with the login page.
Benefit:
Makes the test scripts clean by separating page-specific logic into reusable methods.
 */

public class SauceLoginPage {

    private final WebDriver webDriver;

    private By txtUsername = By.id("user-name");
    private By txtPassword = By.id("password");
    private By btnLogin = By.id("login-button");
    private By lblErrorMessage= By.xpath("h3[data-test='error']");

//    @FindBy(id="user-name")
//    WebElement txtUsername;

//    @FindBy(id="password")
//    WebElement txtPassword;

//    @FindBy(id="login-button")
//    WebElement btnLogin;

//    @FindBy(css="h3[data-test='error']")
//    WebElement lblErrorMessage;

    //constructor
    public SauceLoginPage(WebDriver webDriver){
        if(webDriver==null){
            throw new IllegalArgumentException("WebDriver must not be null");
        }
        this.webDriver=webDriver;
        //PageFactory.initElements(this.webDriver,this);

        //instead of having initialization the elements in the page, during the test run it will dynamically handle the
        //elements
    }

    public SauceLoginPage typeUsername(String username) {
        webDriver.findElement(txtUsername).sendKeys(username);
        return this;
    }
    public SauceLoginPage typePassword(String password) {
        webDriver.findElement(txtPassword).sendKeys(password);
        return this;
    }

    public void clickLogin() {

        webDriver.findElement(btnLogin).click();
    }

    public SauceLoginPage clearUsername() {
        webDriver.findElement(txtUsername).clear();
        return this;
    }

    public SauceLoginPage clearPassword() {
        webDriver.findElement(txtPassword).clear();
        return this;
    }

    public String getErrorMessage() {

        return webDriver.findElement(lblErrorMessage).getText().trim();
    }
}
