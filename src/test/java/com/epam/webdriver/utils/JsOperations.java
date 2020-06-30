package com.epam.webdriver.utils;

import com.epam.webdriver.driver.DriverSingleton;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JsOperations {

    private static final WebDriver DRIVER = DriverSingleton.getDriver().getWebDriver();
    private static final String JS_SCRIPT = "arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');";

    public static void highLightText(WebElement element) {
        ((JavascriptExecutor) DRIVER).executeScript(JS_SCRIPT, element);
    }
}
