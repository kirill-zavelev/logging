package com.epam.webdriver.driver;

import com.epam.webdriver.decorator.DriverDecorator;
import com.epam.webdriver.utils.PropertyLoader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSingleton {

    private static DriverDecorator driver;

    private DriverSingleton() {}

    public static DriverDecorator getDriver() {
        if (null == driver) {
            switch (PropertyLoader.loadProperty("browser")) {
                case  "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new DriverDecorator(new FirefoxDriver());
                    break;
                }
                default: {
                    WebDriverManager.chromedriver().setup();
                    driver = new DriverDecorator(new ChromeDriver());
                }
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
