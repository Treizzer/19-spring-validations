package com.treizer.spring_validations.presentation.advice.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.treizer.spring_validations.presentation.advice.validation.validator.ValidNameValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented // This set signed in the java doc
@Constraint(validatedBy = ValidNameValidator.class)
@Retention(RetentionPolicy.RUNTIME) // Execute in runtime application
// Works in attributes and methods
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface ValidName {

    String message() default "{custom.validation.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
