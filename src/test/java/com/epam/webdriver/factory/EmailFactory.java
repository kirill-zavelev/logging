package com.epam.webdriver.factory;

import com.epam.webdriver.model.Email;

public class EmailFactory {

    public Email getEmail(EmailType type) {

        switch (type) {
            case WITH_EMPTY_SUBJECT:
                return new EmailWithEmptySubjectCreator().createEmail();
            case WITH_TWO_RECIPIENTS:
                return new EmailWithSeveralRecipientsCreator().createEmail();
            default:
                return null;
        }
    }
}
