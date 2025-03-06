package com.hedgerock.spring.mvc_hibernate_aop.utils.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidation.class)
public @interface CheckEmail {

    String value() default "gmail.com";
    String message() default "All emails must be gmail email.com";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
