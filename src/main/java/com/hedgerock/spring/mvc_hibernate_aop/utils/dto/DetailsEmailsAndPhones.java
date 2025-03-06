package com.hedgerock.spring.mvc_hibernate_aop.utils.dto;

import com.hedgerock.spring.mvc_hibernate_aop.utils.validation.CheckEmail;

import java.util.List;

public class DetailsEmailsAndPhones {

    @CheckEmail
    private List<String> emails;

    private List<String> phones;

    public DetailsEmailsAndPhones() {
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }
}
