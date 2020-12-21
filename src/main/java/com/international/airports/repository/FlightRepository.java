package com.international.airports.repository;

import com.international.airports.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

  @Transactional
  @Modifying
  @Query("delete from Flight f where f.id = :id")
  Integer customDeleteById(@Param("id") long id);
}
