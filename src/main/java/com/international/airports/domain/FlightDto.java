package com.international.airports.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class FlightDto {

    private long id;

    private String flightNo;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime departure;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime arrival;

    private String departureAirportName;

    private String arrivalAirportName;

    private String airlineName;

  public long getId() {
    return id;
  }

  public void setId(final long id) {
    this.id = id;
  }

  public String getFlightNo() {
    return flightNo;
  }

  public void setFlightNo(final String flightNo) {
    this.flightNo = flightNo;
  }

  public LocalDateTime getDeparture() {
    return departure;
  }

  public void setDeparture(final LocalDateTime departure) {
    this.departure = departure;
  }

  public LocalDateTime getArrival() {
    return arrival;
  }

  public void setArrival(final LocalDateTime arrival) {
    this.arrival = arrival;
  }

  public String getDepartureAirportName() {
    return departureAirportName;
  }

  public void setDepartureAirportName(final String departureAirportName) {
    this.departureAirportName = departureAirportName;
  }

  public String getArrivalAirportName() {
    return arrivalAirportName;
  }

  public void setArrivalAirportName(final String arrivalAirportName) {
    this.arrivalAirportName = arrivalAirportName;
  }

  public String getAirlineName() {
    return airlineName;
  }

  public void setAirlineName(final String airlineName) {
    this.airlineName = airlineName;
  }
}
