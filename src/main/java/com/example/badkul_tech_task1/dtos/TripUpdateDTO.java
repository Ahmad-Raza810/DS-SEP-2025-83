package com.example.badkul_tech_task1.dtos;

import com.example.badkul_tech_task1.annotation.AtLeastOneFieldRequired;
import com.example.badkul_tech_task1.annotation.ValueOfEnum;
import com.example.badkul_tech_task1.model.Trip;
import com.example.badkul_tech_task1.model.TripStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@AtLeastOneFieldRequired
public class TripUpdateDTO {

    private String destination;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date startDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date endDate;

    @DecimalMin(value = "500.0", message = "price should be at least 500.")
    private Double price;

    @ValueOfEnum(enumClass = TripStatus.class,message = "status must be PLANNED, ONGOING or COMPLETED")
    private String status;

    @JsonIgnore
    @AssertTrue(message = "End date must be After start date.")
    public boolean isValidDateRange() {
        if (startDate == null || endDate==null) {
            return true;
        }
        return endDate.after(startDate);
    }


    //dto to Trip
    public static Trip dtoToTrip(TripUpdateDTO dto){
        Trip trip=new Trip();
        trip.setDestination(dto.getDestination());
        trip.setPrice(dto.getPrice());
        trip.setStartDate(dto.getStartDate());
        trip.setEndDate(dto.getEndDate());
        trip.setStatus(TripStatus.valueOf(dto.getStatus()));

        return trip;

    }
}
