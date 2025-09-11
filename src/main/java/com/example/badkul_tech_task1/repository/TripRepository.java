package com.example.badkul_tech_task1.repository;

import com.example.badkul_tech_task1.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip,Long> {
}
