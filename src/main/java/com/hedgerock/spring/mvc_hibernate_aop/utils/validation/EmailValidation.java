package com.hedgerock.spring.mvc_hibernate_aop.utils.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EmailValidation implements ConstraintValidator<CheckEmail, String> {
    private String endOfEmail;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email.endsWith(this.endOfEmail);
    }

    @Override
    public void initialize(CheckEmail constraintAnnotation) {
        this.endOfEmail = constraintAnnotation.value();
    }
}
