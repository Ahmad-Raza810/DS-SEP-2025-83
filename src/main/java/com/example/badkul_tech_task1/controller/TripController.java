package com.example.badkul_tech_task1.controller;


import com.example.badkul_tech_task1.dtos.TripRequestDTO;
import com.example.badkul_tech_task1.model.Trip;
import com.example.badkul_tech_task1.response.ApiResponse;
import com.example.badkul_tech_task1.service.TripService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }


    //endpoint for creating a trip
    @PostMapping("trip")
    public ResponseEntity<ApiResponse<Trip>> addTrip(@RequestBody @Valid TripRequestDTO dto){
       Trip addedTrip=tripService.addTrip(dto);

       ApiResponse<Trip> response=new ApiResponse<>(
               addedTrip,
               "trip successfully added.",
               HttpStatus.OK.value(),
               LocalDateTime.now()
       );

       return new ResponseEntity<>(response,HttpStatus.OK);
    }



    //endpoint for getting all trips
    @GetMapping("trips")
    public ResponseEntity<ApiResponse<List<Trip>>> getAllTrip(@PathVariable Long id){
        List<Trip> fetchedTrips=tripService.getAllTrip();

        ApiResponse<List<Trip>> response=new ApiResponse<>(
                fetchedTrips,
                "trips fetched successfully.",
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response,HttpStatus.OK);
    }


     //endpoint for getting a trip by id
    @GetMapping("trip/{id}")
    public ResponseEntity<ApiResponse<Trip>> getTripById(@PathVariable Long id){
        Trip fetchedTrip=tripService.getTripById(id);

        ApiResponse<Trip> response=new ApiResponse<>(
                fetchedTrip,
                "trip fetched successfully.",
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
