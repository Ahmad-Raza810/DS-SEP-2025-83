package com.example.badkul_tech_task1.controller;


import com.example.badkul_tech_task1.dtos.TripRequestDTO;
import com.example.badkul_tech_task1.dtos.TripResponseDTO;
import com.example.badkul_tech_task1.dtos.TripUpdateDTO;
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
    public ResponseEntity<ApiResponse<TripResponseDTO>> addTrip(@RequestBody @Valid TripRequestDTO requestDTO){
       Trip addedTrip=tripService.addTrip(requestDTO);
       TripResponseDTO  responseDTO=TripResponseDTO.tripToDto(addedTrip);

       ApiResponse<TripResponseDTO> response=new ApiResponse<>(
               responseDTO,
               "trip successfully added.",
               HttpStatus.OK.value(),
               LocalDateTime.now()
       );
       return new ResponseEntity<>(response,HttpStatus.OK);
    }




    //endpoint for getting all trips
    @GetMapping("trips")
    public ResponseEntity<ApiResponse<List<TripResponseDTO>>> getAllTrip(){
        List<Trip> fetchedTrips=tripService.getAllTrip();

        List<TripResponseDTO> dtos=fetchedTrips.stream().map(TripResponseDTO::tripToDto).toList();

        ApiResponse<List<TripResponseDTO>> response=new ApiResponse<>(
                dtos,
                "trips fetched successfully.",
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }



     //endpoint for getting a trip by id
    @GetMapping("trip/{id}")
    public ResponseEntity<ApiResponse<TripResponseDTO>> getTripById(@PathVariable Long id){
        Trip fetchedTrip=tripService.getTripById(id);
        TripResponseDTO responseDTO=TripResponseDTO.tripToDto(fetchedTrip);
        ApiResponse<TripResponseDTO> response=new ApiResponse<>(
                responseDTO,
                "trip fetched successfully.",
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }



    //endpoint for updating a trip by id
    @PutMapping("trip/{id}")
    public ResponseEntity<ApiResponse<TripResponseDTO>> updateTripById(@PathVariable Long id, TripUpdateDTO dto){
        Trip updatedTrip=tripService.updateTripById(id,dto);
        TripResponseDTO responseDTO=TripResponseDTO.tripToDto(updatedTrip);
        ApiResponse<TripResponseDTO> response=new ApiResponse<>(
                responseDTO,
                "trip fetched successfully.",
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


}
