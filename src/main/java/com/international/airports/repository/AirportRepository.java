package com.international.airports.repository;

import com.international.airports.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

  Optional<Airport> findByName(String name);

  @Query("select a from Airport a where " +
          "lower(a.name) LIKE lower(concat('%', concat(:letters, '%'))) or " +
          "lower(a.city) LIKE lower(concat('%', concat(:letters, '%'))) or " +
          "lower(a.country) LIKE lower(concat('%', concat(:letters, '%')))")
  List<Airport> customFindByNameOrCityOrCountryContainingIgnoreCase(@Param("letters") final String letters);

}
