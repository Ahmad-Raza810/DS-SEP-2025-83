package com.example.badkul_tech_task1.service;

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


    @Override
    public Trip addTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    @Override
    public List<Trip> getAllTrip() {
        return List.of();
    }

    @Override
    public Trip getTripById(Long id) {
        return null;
    }

    @Override
    public Trip updateTripById(Long id, Trip trip) {
        return null;
    }

    @Override
    public void deleteTripById(Long id) {

    }

}
