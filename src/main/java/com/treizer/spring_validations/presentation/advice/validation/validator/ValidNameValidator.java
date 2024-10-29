package com.treizer.spring_validations.presentation.advice.validation.validator;

import com.treizer.spring_validations.presentation.advice.validation.annotation.ValidName;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidNameValidator implements ConstraintValidator<ValidName, String> {

    @Override
    public void initialize(ValidName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Value Cannot be null
        if (value == null) {
            return false;
        }

        // Value Cannot be empty
        if (value.isEmpty()) {
            return false;
        }

        // Value Cannot be greater than 15 charsequence
        if (value.length() > 15) {
            return false;
        }

        // Value cannot be less than 3
        if (value.length() < 3) {
            return false;
        }

        // Value should has first character in uppercase
        if (!Character.isUpperCase(value.charAt(0))) {
            return false;
        }

        return true;
    }

}
