package com.grace.bus_delay_tracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "trips")
public class Trip {

    @Id
    private String tripId;

    private String routeId;
    private String serviceId;
    private String tripHeadsign;
    private String tripShortName;
    private Integer directionId;
    private String shapeId;
    private Integer wheelchairAccessible;
    private Integer bikesAllowed;
    private String blockId;
    private String blockName;

    public Trip(){}

    //declare getters and setters
    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getTripHeadsign() {
        return tripHeadsign;
    }

    public void setTripHeadsign(String tripHeadsign) {
        this.tripHeadsign = tripHeadsign;
    }

    public String getTripShortName() {
        return tripShortName;
    }

    public void setTripShortName(String tripShortName) {
        this.tripShortName = tripShortName;
    }

    public Integer getDirectionId() {
        return directionId;
    }

    public void setDirectionId(Integer directionId) {
        this.directionId = directionId;
    }

    public String getShapeId() {
        return shapeId;
    }

    public void setShapeId(String shapeId) {
        this.shapeId = shapeId;
    }

    public Integer getWheelchairAccessible() {
        return wheelchairAccessible;
    }

    public void setWheelchairAccessible(Integer wheelchairAccessible) {
        this.wheelchairAccessible = wheelchairAccessible;
    }

    public Integer getBikesAllowed() {
        return bikesAllowed;
    }

    public void setBikesAllowed(Integer bikesAllowed) {
        this.bikesAllowed = bikesAllowed;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }
}
