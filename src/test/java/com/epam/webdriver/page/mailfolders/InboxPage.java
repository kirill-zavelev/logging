package com.epam.webdriver.page.mailfolders;

import com.epam.webdriver.decorator.DriverDecorator;
import com.epam.webdriver.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InboxPage extends BasePage {

    @FindBy(xpath = "//div[@data-key='box=messages-item-box']")
    private WebElement emailPreview;

    @FindBy(xpath = "//a[@href='#draft']")
    private WebElement draftFolderBtn;

    @FindBy(xpath = "//a[@href='#sent']")
    private WebElement sendFolderBtn;

    public InboxPage(DriverDecorator driver) {
        super(driver);
    }

    public DraftPage openDraftsFolder() {
        waitForElementToBeClickable(draftFolderBtn);
        draftFolderBtn.click();

        return new DraftPage(driver);
    }

    public InboxPage openSendFolder() {
        waitForElementToBeClickable(sendFolderBtn);
        sendFolderBtn.click();

        return this;
    }
}