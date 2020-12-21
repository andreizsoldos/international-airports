package com.international.airports.repository;

import com.international.airports.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {

  Optional<Airline> findByName(String name);

  @Query("select a from Airline a where " +
          "lower(a.name) LIKE lower(concat('%', concat(:letters, '%'))) or " +
          "lower(a.country) LIKE lower(concat('%', concat(:letters, '%')))")
  List<Airline> customFindByNameOrCountryContainingIgnoreCase(@Param("letters") String letters);

}
