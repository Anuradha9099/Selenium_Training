package com.Sauce;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

import java.rmi.UnexpectedException;

public class Button {
    private final WebElement eleButton;

    public Button(WebElement eleButton) {
        String tagName = eleButton.getTagName();

        if(null!=tagName && "input".equals(tagName.toLowerCase())) {
            this.eleButton = eleButton;
        } else {
            throw new UnexpectedTagNameException("input",tagName);
        }
    }
    public void click() {
        eleButton.click();
    }
}
