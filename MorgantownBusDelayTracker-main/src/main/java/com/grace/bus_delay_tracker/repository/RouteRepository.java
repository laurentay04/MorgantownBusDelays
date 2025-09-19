package com.grace.bus_delay_tracker.repository;

import com.grace.bus_delay_tracker.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RouteRepository extends JpaRepository<Route, String> {
    //crud methods
}
