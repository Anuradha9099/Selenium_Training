package com.Sauce;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

import java.rmi.UnexpectedException;

public class Button {
    private final WebElement eleButton;

    public Button(WebElement eleButton) {
        String tagName = eleButton.getTagName().toLowerCase();
        if ("input".equals(tagName)) {
            String type = eleButton.getDomAttribute("type").toLowerCase();
            if ("submit".equals(type) || "button".equals(type)) {
                this.eleButton = eleButton;
            } else {
                throw new IllegalArgumentException("Expected button type to be 'submit' or 'button', but got: " + type);
            }
        } else {
            throw new UnexpectedTagNameException("input", tagName);
        }
    }

    public void click() {
        eleButton.click();
    }
}
