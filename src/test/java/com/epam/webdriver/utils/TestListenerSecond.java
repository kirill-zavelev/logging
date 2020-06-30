package com.epam.webdriver.utils;

import rp.com.google.common.base.Suppliers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;

import com.epam.reportportal.testng.BaseTestNGListener;
import com.epam.reportportal.testng.ITestNGService;
import com.epam.reportportal.testng.TestNGService;
import java.util.function.Supplier;

import static com.epam.reportportal.listeners.Statuses.FAILED;
import static com.epam.reportportal.listeners.Statuses.PASSED;

public class TestListenerSecond extends BaseTestNGListener {

    private static final Logger LOG = LogManager.getRootLogger();
    private static final Supplier<ITestNGService> SERVICE = Suppliers.memoize(TestNGService::new);

    public TestListenerSecond() {
        super(SERVICE.get());
    }

    @Override
    public void onTestStart(ITestResult testResult) {
        LOG.info("{} started", testResult.getMethod().getDescription());
        SERVICE.get().startTestMethod(testResult);
    }

    @Override
    public void onTestSuccess(ITestResult testResult) {
        LOG.info("{} passed", testResult.getMethod().getDescription());
        SERVICE.get().finishTestMethod(PASSED, testResult);
    }

    @Override
    public void onTestFailure(ITestResult testResult) {
        LOG.info("{} failed", testResult.getMethod().getDescription());
        ScreenShotCreator.saveScreenshot();
        SERVICE.get().sendReportPortalMsg(testResult);
        SERVICE.get().finishTestMethod(FAILED, testResult);

    }

    @Override
    public void onTestSkipped(ITestResult testResult) {
        super.onTestSkipped(testResult);
    }
}
