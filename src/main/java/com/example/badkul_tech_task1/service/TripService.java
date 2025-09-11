package com.example.badkul_tech_task1.service;

import com.example.badkul_tech_task1.model.Trip;

import java.util.List;

public interface TripService {

  Trip addTrip(Trip trip);

  List<Trip> getAllTrip();

  Trip getTripById(Long id);

  Trip updateTripById(Long id,Trip trip);

  void deleteTripById(Long id);

}
