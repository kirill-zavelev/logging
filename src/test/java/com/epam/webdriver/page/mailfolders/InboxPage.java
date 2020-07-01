package com.epam.webdriver.page.mailfolders;

import com.epam.webdriver.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InboxPage extends BasePage {

    @FindBy(xpath = "//div[@data-key='box=messages-item-box']")
    private WebElement emailPreview;

    @FindBy(xpath = "//a[@href='#draft']")
    private WebElement draftFolderBtn;

    @FindBy(xpath = "//a[@href='#sent']")
    private WebElement sendFolderBtn;

    public DraftPage openDraftsFolder() {
        waitForElementToBeClickable(draftFolderBtn);
        draftFolderBtn.click();

        return new DraftPage();
    }

    public InboxPage openSendFolder() {
        waitForElementToBeClickable(sendFolderBtn);
        sendFolderBtn.click();

        return this;
    }
}