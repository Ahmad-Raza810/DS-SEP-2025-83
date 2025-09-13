package com.example.badkul_tech_task1.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValueOfEnumValidator.class)
public @interface ValueOfEnum {

    //enum class
    Class<? extends Enum<?>> enumClass();

    //message field
    String message() default "Invalid value";
    Class<?>[] groups() default {};                    // required by spec (validation groups)
    Class<? extends Payload>[] payload() default {};

}
