package com.epam.webdriver.page;

import com.epam.webdriver.decorator.DriverDecorator;
import com.epam.webdriver.driver.DriverSingleton;
import com.epam.webdriver.utils.JsOperations;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class AbstractPage {

    private static final int TIME_OUT_IN_SECONDS = 10;

    protected final WebDriverWait wait;
    protected JsOperations jsOperations;
    protected DriverDecorator driver;

    protected AbstractPage() {
        this.driver = DriverSingleton.getDriver();
        this.jsOperations = new JsOperations(driver);
        this.wait = new WebDriverWait(driver.getWebDriver(), TIME_OUT_IN_SECONDS);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(TIME_OUT_IN_SECONDS, TimeUnit.SECONDS);
        PageFactory.initElements(driver.getWebDriver(), this);
    }

    protected WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void clickElementWhenItDisplayed(List<WebElement> elements) {

        try {
            elements.forEach(button -> {
                if (button.isDisplayed()) {
                    button.click();
                }
            });
        } catch (StaleElementReferenceException sere) {
            clickElementWhenItDisplayed(elements);
        }

    }

    protected void sendKeysWhenInputIntractable(WebElement element, String text) {
        if (element.isDisplayed()) {
            element.clear();
            element.sendKeys(text);
        }
    }

    public void callContextMenu(WebElement element) {
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
    }
}