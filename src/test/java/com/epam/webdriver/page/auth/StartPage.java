package com.epam.webdriver.page.auth;

import com.epam.webdriver.decorator.DriverDecorator;
import com.epam.webdriver.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StartPage extends AbstractPage {

    @FindBy(className = "user-account__name")
    private WebElement loggedUsername;

    protected StartPage(DriverDecorator driver) {
        super(driver);
    }

    public QuickActionsPanelPage clickOnUsername() {
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(loggedUsername)));
        loggedUsername.click();

        return new QuickActionsPanelPage(driver);
    }
}
