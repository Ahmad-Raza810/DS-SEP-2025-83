package com.example.badkul_tech_task1.service;

import com.example.badkul_tech_task1.dtos.TripRequestDTO;
import com.example.badkul_tech_task1.dtos.TripResponseDTO;
import com.example.badkul_tech_task1.dtos.TripUpdateDTO;
import com.example.badkul_tech_task1.model.Trip;

import java.util.List;

public interface TripService {

  Trip addTrip(TripRequestDTO dto);

  List<Trip> getAllTrip();

  Trip getTripById(Long id);

  Trip updateTripById(Long id, TripUpdateDTO dto);

  void deleteTripById(Long id);

}
