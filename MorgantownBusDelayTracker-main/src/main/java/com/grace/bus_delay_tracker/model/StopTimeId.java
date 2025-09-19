package com.grace.bus_delay_tracker.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StopTimeId implements Serializable {

    private String tripId;

    private int stopSequence;

    // Default constructor
    public StopTimeId() {}

    //constructor
    public StopTimeId(String tripId, int stopSequence) {
        this.tripId = tripId;
        this.stopSequence = stopSequence;
    }

    //getters and setters
    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public int getStopSequence() {
        return stopSequence;
    }

    public void setStopSequence(int stopSequence) {
        this.stopSequence = stopSequence;
    }

    //hashcode and equals for proper composite key behavior
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StopTimeId)) return false;
        StopTimeId that = (StopTimeId) o;
        return stopSequence == that.stopSequence && Objects.equals(tripId, that.tripId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripId, stopSequence);
    }
}
