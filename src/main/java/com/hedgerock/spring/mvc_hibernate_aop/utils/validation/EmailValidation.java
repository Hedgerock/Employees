package com.hedgerock.spring.mvc_hibernate_aop.utils.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EmailValidation implements ConstraintValidator<CheckEmail, List<String>> {
    private String endOfEmail;

    @Override
    public boolean isValid(List<String> emails, ConstraintValidatorContext constraintValidatorContext) {
        for(String email: emails) {
            if (!email.endsWith(this.endOfEmail)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void initialize(CheckEmail constraintAnnotation) {
        this.endOfEmail = constraintAnnotation.value();
    }
}
