package com.example.badkul_tech_task1.annotation;


import com.example.badkul_tech_task1.dtos.TripUpdateDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class AtLeastOneFieldRequiredValidator implements ConstraintValidator<AtLeastOneFieldRequired, TripUpdateDTO> {
    @Override
    public boolean isValid(TripUpdateDTO value, ConstraintValidatorContext context) {

        if (value == null) {
            return false;
        }

        if (StringUtils.hasText(value.getDestination())){
            return true;
        }
        if (StringUtils.hasText(value.getStatus())) {
            return  true;
        }
        if (value.getPrice()!=null) {
            return  true;
        }
        if (value.getStartDate()!=null) {
            return  true;
        }
        return value.getEndDate() != null;

    }
}
