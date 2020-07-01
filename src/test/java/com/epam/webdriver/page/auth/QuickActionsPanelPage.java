package com.epam.webdriver.page.auth;

import com.epam.webdriver.page.AbstractPage;
import com.epam.webdriver.page.mailfolders.InboxPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class QuickActionsPanelPage extends AbstractPage {

    @FindBy(className = "user-account__subname")
    private WebElement loggedUserEmail;

    @FindBy(xpath = "//a[@href='https://mail.yandex.ru']")
    private WebElement mailPage;

    @FindBy(xpath = "//a[contains(@tabindex, '0') and text()='Выйти']")
    private WebElement logoutBtn;

    public String getActualEmail() {

        return loggedUserEmail.getText();
    }

    public InboxPage openMailBox() {
        waitForElementToBeClickable(mailPage).click();

        return new InboxPage();
    }

    public QuickActionsPanelPage clickOnLogoutLink() {
        waitForElementToBeClickable(logoutBtn).click();
        return this;
    }
}
