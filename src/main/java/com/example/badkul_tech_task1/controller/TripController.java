package com.example.badkul_tech_task1.controller;


import com.example.badkul_tech_task1.dtos.TripRequestDTO;
import com.example.badkul_tech_task1.dtos.TripResponseDTO;
import com.example.badkul_tech_task1.dtos.TripUpdateDTO;
import com.example.badkul_tech_task1.model.Trip;
import com.example.badkul_tech_task1.model.TripStatus;
import com.example.badkul_tech_task1.response.ApiResponse;
import com.example.badkul_tech_task1.service.TripService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("api/")
@CrossOrigin("*")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }


    //endpoint for creating a trip
    @PostMapping("trip")
    public ResponseEntity<ApiResponse<TripResponseDTO>> addTrip(@RequestBody @Valid TripRequestDTO requestDTO) {
        Trip addedTrip = tripService.addTrip(requestDTO);
        TripResponseDTO responseDTO = TripResponseDTO.tripToDto(addedTrip);

        ApiResponse<TripResponseDTO> response = new ApiResponse<>(
                responseDTO,
                "trip successfully added.",
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //endpoint for getting all trips
    @GetMapping("trips")
    public ResponseEntity<ApiResponse<Page<TripResponseDTO>>> getAllTrip(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Page<Trip> fetchedPages = tripService.getAllTrip(page, size, sortBy, direction);
        Page<TripResponseDTO> dtos = fetchedPages.map(TripResponseDTO::tripToDto);

        ApiResponse<Page<TripResponseDTO>> response = new ApiResponse<>(
                dtos,
                "trips fetched successfully.",
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //endpoint for getting a trip by id
    @GetMapping("trip/{id}")
    public ResponseEntity<ApiResponse<TripResponseDTO>> getTripById(@PathVariable Long id) {
        Trip fetchedTrip = tripService.getTripById(id);
        TripResponseDTO responseDTO = TripResponseDTO.tripToDto(fetchedTrip);
        ApiResponse<TripResponseDTO> response = new ApiResponse<>(
                responseDTO,
                "trip fetched successfully.",
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //endpoint for updating a trip by id
    @PutMapping("trip/{id}")
    public ResponseEntity<ApiResponse<TripResponseDTO>> updateTripById(@PathVariable Long id, @Valid @RequestBody TripUpdateDTO dto) {
        Trip updatedTrip = tripService.updateTripById(id, dto);
        TripResponseDTO responseDTO = TripResponseDTO.tripToDto(updatedTrip);
        ApiResponse<TripResponseDTO> response = new ApiResponse<>(
                responseDTO,
                "trip updated successfully.",
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //endpoint for deleting a trip by id
    @DeleteMapping("trip/{id}")
    public ResponseEntity<ApiResponse<TripResponseDTO>> deleteTripById(@PathVariable Long id) {
        tripService.deleteTripById(id);
        ApiResponse<TripResponseDTO> response = new ApiResponse<>(
                null,
                "trip deleted successfully.",
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //endpoint for search trip by destination
    @GetMapping("trip/search/{destination}")
    public ResponseEntity<ApiResponse<Page<TripResponseDTO>>> searchTripByDestination(@PathVariable String destination,
                                                                                      @RequestParam(defaultValue = "0") int page,
                                                                                      @RequestParam(defaultValue = "10") int size,
                                                                                      @RequestParam(defaultValue = "id") String sortBy,
                                                                                      @RequestParam(defaultValue = "asc") String direction) {

        Page<Trip> pages = tripService.searchTripByDestination(destination, page, size, sortBy, direction);
        Page<TripResponseDTO> dtos = pages.map(TripResponseDTO::tripToDto);

        ApiResponse<Page<TripResponseDTO>> response = new ApiResponse<>(
                dtos,
                "trips fetched successfully.",
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //endpoint for filter trip by trip status
    @GetMapping("trip/filter")
    public ResponseEntity<ApiResponse<Page<TripResponseDTO>>> filterByTripStatus(
            @RequestParam(defaultValue = "PLANNED") String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "price") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        Page<Trip> pages = tripService.filterByTripStatus(status,page, size, sortBy, direction);
        Page<TripResponseDTO> dtos = pages.map(TripResponseDTO::tripToDto);

        ApiResponse<Page<TripResponseDTO>> response = new ApiResponse<>(
                dtos,
                "trips fetched successfully.",
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //endpoint for filter trip by date range
    @GetMapping("trip/daterange")
    public ResponseEntity<ApiResponse<Page<TripResponseDTO>>> filterByDateRange(
            @RequestParam(defaultValue ="2010-01-01") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(defaultValue ="2025-01-01") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "price") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {


        Page<Trip> pages = tripService.filterByDateRange(startDate,endDate,page, size, sortBy, direction);
        Page<TripResponseDTO> dtos = pages.map(TripResponseDTO::tripToDto);

        ApiResponse<Page<TripResponseDTO>> response = new ApiResponse<>(
                dtos,
                "trips fetched successfully.",
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
