package com.international.airports.repository;

import com.international.airports.model.Airline;
import com.international.airports.model.Airport;
import com.international.airports.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

  Flight findById(long id);

  @Transactional
  @Modifying(clearAutomatically = true)
  @Query("delete from Flight f where f.id = :id")
  Integer customDeleteById(@Param("id") final long id);

  @Transactional
  @Modifying(clearAutomatically = true)
  @Query("update Flight f set " +
          "f.flightNo = :flightNo, " +
          "f.departure = :departureDateTime," +
          "f.departureAirport = :departureAirport," +
          "f.arrival = :arrivalDateTime," +
          "f.arrivalAirport = :arrivalAirport," +
          "f.airline = :airline " +
          "where f.id = :id")
  Integer customUpdateById(@Param("id") final long id,
                           @Param("flightNo") final String flightNo,
                           @Param("departureDateTime") final LocalDateTime departureDateTime,
                           @Param("departureAirport") final Airport departureAirport,
                           @Param("arrivalDateTime") final LocalDateTime arrivalDateTime,
                           @Param("arrivalAirport") final Airport arrivalAirport,
                           @Param("airline") final Airline airline);
}
