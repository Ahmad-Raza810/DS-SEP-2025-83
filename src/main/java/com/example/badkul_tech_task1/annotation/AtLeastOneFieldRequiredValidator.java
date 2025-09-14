package com.example.badkul_tech_task1.annotation;


import com.example.badkul_tech_task1.dtos.TripUpdateDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class AtLeastOneFieldRequiredValidator implements ConstraintValidator<AtLeastOneFieldRequired, TripUpdateDTO> {
    @Override
    public boolean isValid(TripUpdateDTO dto, ConstraintValidatorContext context) {
        if (dto == null) {
            return false;
        }

        return   StringUtils.hasText(dto.getDestination()) || // check non-null & not empty
                dto.getStartDate() != null ||
                dto.getEndDate() != null ||
                dto.getPrice() != null ||
                StringUtils.hasText(dto.getStatus());
    }

}
