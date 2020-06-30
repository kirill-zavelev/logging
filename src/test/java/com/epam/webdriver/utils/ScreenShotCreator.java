package com.epam.webdriver.utils;

import com.epam.reportportal.message.ReportPortalMessage;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import com.epam.webdriver.driver.DriverSingleton;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class ScreenShotCreator {

    private static final Logger LOGGER = LogManager.getLogger("logger");
    private static final String SCREENSHOT_FILE_PATH = ".//target/screenshots/";
    private static final String SCREENSHOT_FILE_FORMAT = ".png";

    public static void saveScreenshot() {

        File screenCapture = ((TakesScreenshot) DriverSingleton
                .getDriver()
                .getWebDriver())
                .getScreenshotAs(OutputType.FILE);
        try {
            String currentTimeAsString = DateUtils.getCurrentTimeAsString();
            FileUtils.copyFile(screenCapture, new File(
                    SCREENSHOT_FILE_PATH
                            + currentTimeAsString
                            + SCREENSHOT_FILE_FORMAT)
            );
            LOGGER.info("Screenshot saved to : " + SCREENSHOT_FILE_PATH + " | name : " + currentTimeAsString + SCREENSHOT_FILE_FORMAT);
            LOGGER.info(new ReportPortalMessage(screenCapture, "Screenshot:"));
        } catch (IOException ioe) {
            LOGGER.error("Failed to save screenshot: " + ioe.getLocalizedMessage());
        }
    }
}
