package com.example.badkul_tech_task1.etc;

import com.example.badkul_tech_task1.model.Trip;
import com.example.badkul_tech_task1.model.TripStatus;
import com.example.badkul_tech_task1.repository.TripRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final TripRepository tripRepository;

    public DataLoader(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        String[] cities = {"Mumbai","Delhi","Bangalore","Hyderabad","Chennai","Kolkata","Jaipur","Pune","Ahmedabad","Goa"};
        String[] statuses = {"PLANNED","ONGOING","COMPLETED"};

        if (tripRepository.count()==0) {
            for (int i = 1; i <= 100; i++) {
                Trip trip = new Trip();
                trip.setDestination(cities[i % cities.length]);
                trip.setStartDate(LocalDate.of(2024, 10, 1).plusDays(i));
                trip.setEndDate(LocalDate.of(2024, 10, 6).plusDays(i));
                trip.setPrice(500.0 + (i * 10));
                trip.setStatus(TripStatus.valueOf(statuses[i % statuses.length]));
                tripRepository.save(trip);
            }
            System.out.println("100 trips inserted successfully.");
        }
        else {
            System.out.println("data already exists.");
        }
    }
}

