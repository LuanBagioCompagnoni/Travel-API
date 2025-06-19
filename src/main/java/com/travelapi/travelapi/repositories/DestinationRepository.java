package com.travelapi.travelapi.repositories;

import com.travelapi.travelapi.models.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

    @Query("SELECT d FROM Destination d WHERE " +
            "LOWER(d.name) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "LOWER(d.location) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "LOWER(d.description) LIKE LOWER(CONCAT('%', :term, '%'))")
    List<Destination> searchByTerm(String term);
}