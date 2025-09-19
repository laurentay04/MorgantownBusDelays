package com.grace.bus_delay_tracker.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//bus delay record class for the db
//maps to bus_delay
@Entity
@Table(name = "bus_delay")
public class BusDelay {

   
    //primary key for each delay record, auto increments
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //unique route identifier from GTFS file data
    @Column(name = "route_id", nullable = false)
    private String routeId;

   
    //make it readable to display for frontend
    @Column(name = "route_name", nullable = false)
    private String routeName;

  
    //description of the delay, need to see about changing to 50
    @Column(name = "description", length = 500)
    private String description;


    //delay timestamp
    @Column(name = "report_time", nullable = false)
    private LocalDateTime reportTime;

 
    //declare constructors required by jpa/hibernate
    public BusDelay(){}

    /**
     * @param routeId The unique route identifier
     * @param routeName The display name of the route
     * @param description Optional description of the delay
     * @param reportTime When the delay was reported
     */
    public BusDelay(String routeId, String routeName, String description, LocalDateTime reportTime) {
        this.routeId = routeId;
        this.routeName = routeName;
        this.description = description;
        this.reportTime = reportTime;
    }

    //also equired by JPA/Hibernate for database operations
    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getReportTime() {
        return reportTime;
    }

    public void setReportTime(LocalDateTime reportTime) {
        this.reportTime = reportTime;
    }




}
