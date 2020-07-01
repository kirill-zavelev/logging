package com.epam.webdriver.base;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import com.epam.webdriver.decorator.DriverDecorator;
import com.epam.webdriver.driver.DriverSingleton;
import com.epam.webdriver.factory.EmailFactory;
import com.epam.webdriver.page.auth.LoginPage;
import com.epam.webdriver.page.auth.QuickActionsPanelPage;
import com.epam.webdriver.page.mailactions.MailCreationPage;
import com.epam.webdriver.page.mailfolders.DraftPage;
import com.epam.webdriver.page.mailfolders.InboxPage;
import com.epam.webdriver.utils.PropertyLoader;
import com.epam.webdriver.utils.TestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({ReportPortalTestNGListener.class, TestListener.class})
public class BaseTest {

    private static final Logger LOGGER = LogManager.getLogger("logger");
    private static final String USERNAME = PropertyLoader.loadProperty("user.name");
    private static final String PASSWORD = PropertyLoader.loadProperty("user.password");
    protected static final String EMAIL = PropertyLoader.loadProperty("user.send.from");
    private static final String BASE_URL = PropertyLoader.loadProperty("base.url");
    protected static final EmailFactory EMAIL_FACTORY = new EmailFactory();

    protected LoginPage loginPage;
    protected QuickActionsPanelPage quickActionsPanelPage;
    protected InboxPage inboxPage;
    protected MailCreationPage mailCreationPage;
    protected DraftPage draftPage;

    private DriverDecorator driver;

    @BeforeMethod
    public void setUpBrowser() {
        LOGGER.info("Test started");
        driver = DriverSingleton.getDriver();

        loginPage = new LoginPage(driver);
        mailCreationPage = new MailCreationPage(driver);
        draftPage = new DraftPage(driver);
        inboxPage = new InboxPage(driver);

        quickActionsPanelPage = loginPage
                .openPage(BASE_URL)
                .login(USERNAME, PASSWORD)
                .clickOnUsername();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownBrowser() {
        DriverSingleton.closeDriver();
        LOGGER.info("Test finished");
    }
}
