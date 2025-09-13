package com.example.badkul_tech_task1.dtos;

import com.example.badkul_tech_task1.annotation.ValueOfEnum;
import com.example.badkul_tech_task1.model.Trip;
import com.example.badkul_tech_task1.model.TripStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripRequestDTO {

    @NotBlank(message = "destination is required.")
    private String destination;

    @NotNull(message = "start date is required.")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date startDate;

    @NotNull(message = "end date is required.")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date endDate;

    @NotNull(message = "price is required.")
    @DecimalMin(value = "500.0", message = "price should be at least 500.")
    private Double price;

    @NotBlank(message = "status is required.")
    @ValueOfEnum(enumClass = TripStatus.class,message = "status must be PLANNED, ONGOING or COMPLETED")
    @Enumerated(EnumType.STRING)
    private String status;

    @AssertTrue(message = "End date must be After start date.")
    public boolean isValidDateRange() {
        if (startDate == null || endDate==null) {
            return false;
        }
        return endDate.after(startDate);
    }


    //method which convert dto object into Trip object
    public static Trip dtoToTrip(TripRequestDTO dto){
        Trip trip=new Trip();
        trip.setDestination(dto.getDestination());
        trip.setStatus(TripStatus.valueOf(dto.getStatus()));
        trip.setPrice(dto.getPrice());
        trip.setStartDate(dto.getStartDate());
        trip.setEndDate(dto.getEndDate());

        return trip;
    }
}
