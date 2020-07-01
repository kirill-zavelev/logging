package com.epam.webdriver.utils;

import com.epam.webdriver.decorator.DriverDecorator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JsOperations {

    private static final String JS_SCRIPT = "arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');";
    private DriverDecorator driver;

    public JsOperations(DriverDecorator driver) {
        this.driver = driver;
    }

    public void highLightText(WebElement element) {
        ((JavascriptExecutor) driver.getWebDriver()).executeScript(JS_SCRIPT, element);
    }
}
