package com.grace.bus_delay_tracker.model;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "stop_times")
public class StopTime {

    @EmbeddedId
    private StopTimeId id;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @Column(name = "stop_id")
    private String stopId;

    public StopTime() {}

    public StopTime(StopTimeId id, LocalTime arrivalTime, LocalTime departureTime, String stopId) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.stopId = stopId;
    }

    public StopTimeId getId() {
        return id;
    }

    public void setId(StopTimeId id) {
        this.id = id;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }
}
