package com.epam.webdriver.decorator;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import com.epam.webdriver.utils.TestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;

import java.util.List;
import java.util.Set;

@Listeners({ReportPortalTestNGListener.class, TestListener.class})
public class DriverDecorator implements WebDriver {

    private static final Logger LOGGER = LogManager.getLogger("logger");
    private WebDriver driver;

    public DriverDecorator(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    @Override
    public void get(String s) {
        driver.get(s);
    }

    @Override
    public String getCurrentUrl() {
        LOGGER.info("Driver gets current url");

        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        WebElement element = null;

        try {
            element = driver.findElement(by);
            LOGGER.info("Element found by : " + by.toString());
        } catch (NoSuchElementException nse) {
            LOGGER.error("Element con not be found by : " + by.toString());
        }

        return element;
    }

    @Override
    public String getPageSource() {
        return driver.getPageSource();
    }

    @Override
    public void close() {
        driver.close();
    }

    @Override
    public void quit() {
        driver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return driver.navigate();
    }

    @Override
    public Options manage() {
        return driver.manage();
    }
}
