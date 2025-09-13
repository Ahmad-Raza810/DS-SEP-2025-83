package com.example.badkul_tech_task1.annotation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum,String> {

    private Set<String> acceptedValues;


    @Override
    public void initialize(ValueOfEnum constraintAnnotation) {
        acceptedValues=Arrays.stream(constraintAnnotation.enumClass()
                .getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value==null)
            return true;
        return acceptedValues.contains(value);
    }
}
