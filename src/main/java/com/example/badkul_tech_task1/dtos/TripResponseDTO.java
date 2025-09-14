package com.example.badkul_tech_task1.dtos;

import com.example.badkul_tech_task1.model.Trip;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripResponseDTO {

    private Long id;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double price;
    private String status;

    public static TripResponseDTO tripToDto(Trip trip){
        TripResponseDTO dto=new TripResponseDTO();
        dto.setId(trip.getId());
        dto.setDestination(trip.getDestination());
        dto.setPrice(trip.getPrice());
        dto.setStatus(String.valueOf(trip.getStatus()));
        dto.setStartDate(trip.getStartDate());
        dto.setEndDate(trip.getEndDate());
        return dto;
    }
}

