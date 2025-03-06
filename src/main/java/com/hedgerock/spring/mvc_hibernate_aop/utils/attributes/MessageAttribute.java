package com.hedgerock.spring.mvc_hibernate_aop.utils.attributes;

public class MessageAttribute {
    public static final String MESSAGE_ATTR_TITLE = "message";
    public static final String TEMPLATE_OF_SUCCESS_OPERATION = "%s has successfully %s :)";
    public static final String TEMPLATE_OF_FAILED_OPERATION = "Failed to %s %s :( %s";
    public static final String TEMPLATE_OF_NOT_FOUND = "%s not found :(";

    private MessageAttribute() {}

}
