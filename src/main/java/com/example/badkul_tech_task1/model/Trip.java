package com.example.badkul_tech_task1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trip {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @NotNull(message = "status is required.")
    @Enumerated(EnumType.STRING)
    private TripStatus status;

    @JsonIgnore
    @AssertTrue(message = "End date must be After start date.")
        public boolean isValidDateRange() {
        if (startDate != null && endDate != null) return endDate.after(startDate);
        return false;
    }

}
