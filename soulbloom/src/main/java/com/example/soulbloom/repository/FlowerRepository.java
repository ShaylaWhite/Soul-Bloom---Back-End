package com.example.soulbloom.repository;

import com.example.soulbloom.model.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface FlowerRepository extends JpaRepository<Flower, Long> {
    // Add any custom queries or methods related to Flower here
    Optional<Flower> findByIdAndUser_Id(Long flowerId, Long userId);
    List<Flower> findByUser_Id(Long userId);
}
