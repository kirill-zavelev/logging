package com.epam.webdriver.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

public class TestListener implements ITestListener {

    private static final Logger LOGGER = LogManager.getLogger("logger");

    private static final String TEST_STARTED_MESSAGE = " is started";
    private static final String TEST_PASSED_MESSAGE = " is passed";
    private static final String TEST_SKIPPED_MESSAGE = " is skipped";
    private static final String TEST_FAILED_MESSAGE = " is failed";

    public void onTestStart(ITestResult result) {
        LOGGER.info(result.getName() + TEST_STARTED_MESSAGE);
    }

    public void onTestSuccess(ITestResult result) {
        LOGGER.info(result.getName() + TEST_PASSED_MESSAGE);
    }

    public void onTestSkipped(ITestResult result) {
        LOGGER.warn(TEST_SKIPPED_MESSAGE);
        ScreenShotCreator.saveScreenshot();
    }

    public void onTestFailure(ITestResult result) {
        LOGGER.error(result.getName() + TEST_FAILED_MESSAGE);
        ScreenShotCreator.saveScreenshot();
    }

    public void onStart(ITestContext context) {

    }

    public void onFinish(ITestContext context) {

    }
}
