package com.example.badkul_tech_task1.service;

import com.example.badkul_tech_task1.exception.ResourceNotFoundException;
import com.example.badkul_tech_task1.model.Trip;
import com.example.badkul_tech_task1.repository.TripRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripServiceImpl implements TripService{

    private final TripRepository tripRepository;

    public TripServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

   //service method for for creating a trip
    @Override
    public Trip addTrip(Trip trip) {
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
    public Trip updateTripById(Long id, Trip trip) {
        return null;
    }

    //service method for deleting a trip
    @Override
    public void deleteTripById(Long id) {

    }

}
