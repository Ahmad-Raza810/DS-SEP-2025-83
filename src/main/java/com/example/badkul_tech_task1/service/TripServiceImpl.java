package com.example.badkul_tech_task1.service;

import com.example.badkul_tech_task1.dtos.TripRequestDTO;
import com.example.badkul_tech_task1.dtos.TripResponseDTO;
import com.example.badkul_tech_task1.dtos.TripUpdateDTO;
import com.example.badkul_tech_task1.exception.ResourceNotFoundException;
import com.example.badkul_tech_task1.model.Trip;
import com.example.badkul_tech_task1.repository.TripRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class TripServiceImpl implements TripService{

    private final TripRepository tripRepository;

    public TripServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

   //service method for creating a trip
    @Override
    public TripResponseDTO addTrip(TripRequestDTO dto) {
        Trip trip=TripRequestDTO.dtoToTrip(dto);
        return TripResponseDTO.tripToDto(tripRepository.save(trip));
    }

    //service method for get all trip
    @Override
    public List<TripResponseDTO> getAllTrip() {

        List<Trip> trips=tripRepository.findAll();
        return trips.stream().map(TripResponseDTO::tripToDto).toList();
    }

    //service method for get trip by id
    @Override
    public TripResponseDTO getTripById(Long id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found with id " + id));

    }

    //service method for updating a trip
    @Override
    public Trip updateTripById(Long id, TripUpdateDTO dto) {
       Trip trip=tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found with id " + id));

        Trip updatedTrip=TripUpdateDTO.dtoToTrip(dto);

        if (StringUtils.hasText(updatedTrip.getDestination())) {
            trip.setDestination(updatedTrip.getDestination());
        }

        if (updatedTrip.getStartDate()!=null) {
            trip.setStartDate(updatedTrip.getStartDate());
        }

        if (updatedTrip.getEndDate()!=null) {
            trip.setEndDate(updatedTrip.getEndDate());
        }

        if (updatedTrip.getStatus()!=null) {
            trip.setStatus(updatedTrip.getStatus());
        }

        if (updatedTrip.getPrice()!=null) {
            trip.setPrice(updatedTrip.getPrice());
        }

        return tripRepository.save(trip);
    }

    //service method for deleting a trip
    @Override
    public void deleteTripById(Long id) {

    }

}
