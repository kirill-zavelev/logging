package com.epam.webdriver.page.auth;

import com.epam.webdriver.decorator.DriverDecorator;
import com.epam.webdriver.page.AbstractPage;
import com.epam.webdriver.utils.JsOperations;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {

    @FindBy(id = "passp-field-login")
    private WebElement userName;

    @FindBy(id = "passp-field-passwd")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitLoginBtn;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitPasswordBtn;

    @FindBy(className = "passp-footer")
    private WebElement footer;

    public LoginPage(DriverDecorator driver) {
        super(driver);
    }

    private LoginPage setUserName(String username) {
        userName.sendKeys(username);
        JsOperations.highLightText(userName);

        return this;
    }

    private LoginPage clickLogin() {
        wait.until(ExpectedConditions.visibilityOf(footer));
        submitLoginBtn.click();

        return this;
    }

    private LoginPage setPasswordField(String password) {
        passwordField.sendKeys(password);

        return this;
    }

    private LoginPage clickPassword() {
        submitPasswordBtn.click();

        return this;
    }

    public StartPage login(String username, String password) {
        setUserName(username);
        clickLogin();
        setPasswordField(password);
        clickPassword();

        return new StartPage(driver);
    }

    public boolean isPasswordInputDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(passwordField)).isDisplayed();
    }
}
