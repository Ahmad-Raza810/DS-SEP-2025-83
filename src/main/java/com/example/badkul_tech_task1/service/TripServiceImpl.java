package com.example.badkul_tech_task1.service;

import com.example.badkul_tech_task1.dtos.TripRequestDTO;
import com.example.badkul_tech_task1.dtos.TripResponseDTO;
import com.example.badkul_tech_task1.dtos.TripUpdateDTO;
import com.example.badkul_tech_task1.exception.ResourceNotFoundException;
import com.example.badkul_tech_task1.model.Trip;
import com.example.badkul_tech_task1.model.TripStatus;
import com.example.badkul_tech_task1.repository.TripRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TripServiceImpl implements TripService{

    private final TripRepository tripRepository;

    public TripServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }


   //service method for creating a trip
    @Override
    public Trip addTrip(TripRequestDTO dto) {
        Trip trip=TripRequestDTO.dtoToTrip(dto);
        return tripRepository.save(trip);
    }


    //service method for get all trip
    @Override
    public List<Trip> getAllTrip() {
        return tripRepository.findAll();
    }



    //service method for get trip by id
    @Override
    public Trip getTripById(Long id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found with id " + id));

    }


    //service method for updating a trip
    @Override
    public Trip updateTripById(Long id, TripUpdateDTO dto) {
       Trip trip=tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found with id " + id));

        // Update destination if provided
        if (StringUtils.hasText(dto.getDestination())) {
            trip.setDestination(dto.getDestination());
        }

        // Update startDate if provided and fix 2-digit year
        if (dto.getStartDate() != null) {
            trip.setStartDate(dto.getStartDate());
        }

        // Update endDate if provided and fix 2-digit year
        if (dto.getEndDate() != null) {
            trip.setEndDate(dto.getEndDate());
        }

        // Update status if provided
        if (StringUtils.hasText(dto.getStatus())) {
            trip.setStatus(TripStatus.valueOf(dto.getStatus()));
        }

        // Update price if provided
        if (dto.getPrice() != null) {
            trip.setPrice(dto.getPrice());
        }

        return tripRepository.save(trip);
    }

    //service method for deleting a trip
    @Override
    public void deleteTripById(Long id) {

    }

//    //extra utility method
//    private Date fixYear(Date date) {
//        if (date == null) return null;
//
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        int year = cal.get(Calendar.YEAR);
//
//        if (year < 100) {          // If user entered '25', convert to 2025
//            cal.set(Calendar.YEAR, 2000 + year);
//        }
//
//        return cal.getTime();
//    }

}
