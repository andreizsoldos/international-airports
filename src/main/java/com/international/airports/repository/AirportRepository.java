package com.international.airports.repository;

import com.international.airports.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

  Optional<Airport> findByName(String name);

  List<Airport> findByNameContainingIgnoreCase(@Param("name") String name);

}
