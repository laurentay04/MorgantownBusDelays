package com.grace.bus_delay_tracker.controller;

import com.grace.bus_delay_tracker.model.Route;
import com.grace.bus_delay_tracker.repository.RouteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


 //controller for accessing route data via HTTP endpoints

@RestController
@RequestMapping("/routes")
public class RouteController {

    private final RouteRepository routeRepository;

    public RouteController(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }


     // GET /routes

    @GetMapping
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }


     // GET /routes/{id}

    @GetMapping("/{id}")
    public Route getRouteById(@PathVariable String id) {
        return routeRepository.findById(id).orElse(null);
    }
}
