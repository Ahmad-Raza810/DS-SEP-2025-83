package com.example.badkul_tech_task1.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AtLeastOneFieldRequiredValidator.class)
public @interface AtLeastOneFieldRequired {

    String message() default "at least one field required for update.";
    Class<?>[] groups() default {};                    // required by spec (validation groups)
    Class<? extends Payload>[] payload() default {};
}
