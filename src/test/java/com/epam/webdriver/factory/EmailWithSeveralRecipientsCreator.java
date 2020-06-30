package com.epam.webdriver.factory;

import com.epam.webdriver.model.Email;
import com.epam.webdriver.utils.PropertyLoader;

public class EmailWithSeveralRecipientsCreator implements EmailCreator {

    @Override
    public Email createEmail() {
        Email emailWithTwoRecipients = new Email();

        emailWithTwoRecipients.setRecipient(PropertyLoader.loadProperty("user.send.to")
                + " ;" + "k.zavelev@gmail.com");
        emailWithTwoRecipients.setSubject(emailWithTwoRecipients.getSubject());
        emailWithTwoRecipients.setBody(emailWithTwoRecipients.getBody());

        return emailWithTwoRecipients;
    }
}
