package com.example.badkul_tech_task1.service;

import com.example.badkul_tech_task1.dtos.TripRequestDTO;
import com.example.badkul_tech_task1.dtos.TripUpdateDTO;
import com.example.badkul_tech_task1.model.Trip;
import org.springframework.data.domain.Page;


public interface TripService {

    Trip addTrip(TripRequestDTO dto);

    Page<Trip> getAllTrip(int page, int size, String sortBy, String direction);

    Trip getTripById(Long id);

    Trip updateTripById(Long id, TripUpdateDTO dto);

    void deleteTripById(Long id);

    Page<Trip> searchTripByDestination(String destination, int page, int size, String sortBy, String direction);

}
