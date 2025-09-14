package com.example.badkul_tech_task1.service;

import com.example.badkul_tech_task1.dtos.TripRequestDTO;
import com.example.badkul_tech_task1.dtos.TripUpdateDTO;
import com.example.badkul_tech_task1.exception.ResourceNotFoundException;
import com.example.badkul_tech_task1.model.Trip;
import com.example.badkul_tech_task1.model.TripStatus;
import com.example.badkul_tech_task1.repository.TripRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;

    public TripServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }


    //service method for creating a trip
    @Override
    public Trip addTrip(TripRequestDTO dto) {
        Trip trip = TripRequestDTO.dtoToTrip(dto);
        return tripRepository.save(trip);
    }


    //service method for get all trip
    @Override
    public Page<Trip> getAllTrip(int page, int size, String sortBy, String direction) {

        //sorting
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        //pagination
        Pageable pageable = PageRequest.of(page, size, sort);

        return tripRepository.findAll(pageable);
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
        Trip trip = tripRepository.findById(id)
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
        tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found with id " + id));

        tripRepository.deleteById(id);


    }

    @Override
    public Page<Trip> searchTripByDestination(String destination, int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Trip> trips = tripRepository.findByDestinationContainingIgnoreCase(destination, pageable);
        if (trips.isEmpty()) {
            throw new ResourceNotFoundException("trips not found with destination " + destination);
        }

        return trips;

    }

}
