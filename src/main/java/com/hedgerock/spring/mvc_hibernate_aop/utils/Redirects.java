package com.hedgerock.spring.mvc_hibernate_aop.utils;

public class Redirects {
    public static final String REDIRECT_FOR_SPECIFIC_PAGE = "redirect:/%s?%s=%d";
    public static final String REDIRECT_FOR_SPECIFIC_PAGE_EXTENDED = REDIRECT_FOR_SPECIFIC_PAGE + "&%s=%d";
    public static final String REDIRECT_TO_THE_MAIN_PAGE = "redirect:/";
    public static final String REDIRECT_TO_NOT_FOUND_PAGE = "redirect:/notFoundPage";

    private Redirects () {}
}
