package com.temp.pages;

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

    @FindBy(id="user-name")
    WebElement txtUsername;

    @FindBy(id="password")
    WebElement txtPassword;

    @FindBy(id="login-button")
    WebElement btnLogin;

    @FindBy(css="h3[data-test='error']")
    WebElement lblErrorMessage;

    //constructor
    public SauceLoginPage(WebDriver webDriver){
        this.webDriver=webDriver;
        PageFactory.initElements(this.webDriver,this);
    }

    public SauceLoginPage typeUsername(String username) {
        txtUsername.sendKeys(username);
        return this;
    }
    public SauceLoginPage typePassword(String password) {
        txtPassword.sendKeys(password);
        return this;
    }

    public void clickLogin() {
        btnLogin.click();
    }

    public SauceLoginPage clearUsername() {
        txtUsername.clear();
        return this;
    }

    public SauceLoginPage clearPassword() {
        txtPassword.clear();
        return this;
    }

    public String getErrorMessage() {
        return lblErrorMessage.getText().trim();
    }
}
