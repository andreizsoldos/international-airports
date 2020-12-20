package com.international.airports.repository;

import com.international.airports.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {

  Optional<Airline> findByName(String name);

  List<Airline> findByNameContainingIgnoreCase(@Param("name") String name);

}
