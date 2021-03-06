package com.epam.webdriver.test;

import com.epam.webdriver.base.BaseTest;
import com.epam.webdriver.factory.EmailType;
import com.epam.webdriver.model.Email;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MailTest extends BaseTest {

    @Test(groups = {"smoke"})
    public void loginTest() {
        String actualEmail = quickActionsPanelPage
                .getActualEmail();

        Assert.assertEquals(actualEmail, EMAIL, "Incorrect email is displayed.");
    }

    @Test(groups = {"regression"})
    public void sendEmailToDraftTest() {
        Email expectedEmail = EMAIL_FACTORY.getEmail(EmailType.WITH_EMPTY_SUBJECT);

        quickActionsPanelPage.openMailBox();

        inboxPage.openMailCreationForm();

        mailCreationPage
                .fillEmail(expectedEmail)
                .sendMailAsDraft();

        inboxPage.openDraftsFolder();

        Email actualEmail = draftPage.getActualEmailFromList(expectedEmail);

        Assert.assertEquals(actualEmail, expectedEmail, "Incorrect email data. Please mail parameters.");
    }

    @Test(groups = {"smoke"})
    public void deleteEmailFromDraftTest() {
        Email email = new Email();

        quickActionsPanelPage.openMailBox()
                .openMailCreationForm();

        mailCreationPage.fillEmail(email)
                .sendMailAsDraft();

        inboxPage.openDraftsFolder();

        boolean isEmailDeleted = draftPage
                .checkEmailCheckbox(email)
                .clickDeleteEmail()
                .isEmailDeleted(email);

        Assert.assertTrue(isEmailDeleted, "Email was not deleted from inbox.");
    }

    @Test(groups = {"regression"})
    public void updateEmailTest() {
        Email email = new Email();
        Email expectedEmailToBeUpdated = new Email();

        quickActionsPanelPage
                .openMailBox()
                .openMailCreationForm();

        mailCreationPage.fillEmail(email)
                .sendMailAsDraft();

        inboxPage
                .openDraftsFolder()
                .openEmail(email);

        mailCreationPage.fillEmail(expectedEmailToBeUpdated)
                .sendMail();

        Email actualEmailToBeUpdated = inboxPage
                .openSendFolder()
                .getActualEmailFromList(expectedEmailToBeUpdated);

        Assert.assertEquals(actualEmailToBeUpdated, expectedEmailToBeUpdated, "Email was not updated.");
    }

    @Test(groups = {"smoke"})
    public void sendEmailTest() {
        Email expectedEmail = new Email();

        quickActionsPanelPage.openMailBox();

        inboxPage.openMailCreationForm();

        mailCreationPage
                .fillEmail(expectedEmail)
                .sendMail();

        Email actualEmail = inboxPage
                .openSendFolder()
                .getActualEmailFromList(expectedEmail);

        Assert.assertEquals(actualEmail, expectedEmail, "Expected email does not exist in the list.");
    }

    @Test(groups = {"smoke"})
    public void logoutTest() {
        quickActionsPanelPage.clickOnLogoutLink();

        boolean isPasswordInputInteractable = loginPage.isPasswordInputDisplayed();

        Assert.assertTrue(isPasswordInputInteractable, "User was not logged out.");
    }
}
