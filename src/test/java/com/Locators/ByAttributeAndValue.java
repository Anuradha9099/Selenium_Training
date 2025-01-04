package com.Locators;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ByAttributeAndValue extends By{

    private final String attribute;
    private final String value;

    public ByAttributeAndValue(String attribute, String value){
        this.attribute=attribute;
        this.value=value;
    }

    /*
    SearchContext: A Selenium interface, usually implemented by a WebDriver or WebElement,
     used to search elements in a web page.

    List<WebElement>: The return type, a list of WebElement objects found by the given SearchContext
     */

    @Override
    public List<WebElement> findElements(SearchContext webDriver) {
        return webDriver.findElements(By.cssSelector(String.format("[%s='%s']", attribute, value)));
    }
}
