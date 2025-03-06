package com.hedgerock.spring.mvc_hibernate_aop.utils.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String firstFieldName;
    private String secondFieldName;
    private String message;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.firstFieldName = constraintAnnotation.first();
        this.secondFieldName = constraintAnnotation.second();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
       try {
           Object firstValue = new BeanWrapperImpl(value).getPropertyValue(this.firstFieldName);
           Object secondValue = new BeanWrapperImpl(value).getPropertyValue(this.secondFieldName);

           boolean valid = firstValue != null && firstValue.equals(secondValue);

           if (!valid) {
               context.disableDefaultConstraintViolation();
               context.buildConstraintViolationWithTemplate(this.message)
                       .addPropertyNode(this.secondFieldName)
                       .addConstraintViolation();
           }

           return valid;
       } catch (Exception e) {
            return false;
       }
    }

}
