package com.example.soulbloom.repository;

import com.example.soulbloom.model.Garden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GardenRepository extends JpaRepository<Garden, Long> {
    // Add any custom queries or methods related to Garden here
//    Optional<Garden> findByIdAndUser_Id(Long gardenId, Long userId);
    Optional<Garden> findByUser_Id(Long userId);
}
