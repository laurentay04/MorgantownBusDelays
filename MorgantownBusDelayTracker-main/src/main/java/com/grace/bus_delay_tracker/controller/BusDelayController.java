package com.grace.bus_delay_tracker.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grace.bus_delay_tracker.model.BusDelay;
import com.grace.bus_delay_tracker.service.BusDelayService;


/**
 * Class handles http requests
 * values ore serialized to JSON or XML
*
* Exposes API endpoints under /api/delays path to get and post bus data
*
 * */

@RestController
@RequestMapping("/api/delays")
//allow crossorigin requests from any domain
@CrossOrigin(origins = "*")
public class BusDelayController {

    private static final Logger logger = LoggerFactory.getLogger(BusDelayController.class);
    private final BusDelayService service;

    //contructor injection
    public BusDelayController(BusDelayService service) {
        this.service = service;
    }

    //handle get requests and return all bus delay records
    @GetMapping
    public List<BusDelay> getAllDelays() {
        logger.info("GET /api/delays - Fetching top 5 most recent delays");
        try {
            List<BusDelay> delays = service.getAllDelays();
            logger.info("Successfully retrieved {} delays", delays.size());
            //log the delays with their timestamps for debugging
            for (BusDelay delay : delays) {
                logger.info("Delay ID: {}, Route: {}, Time: {}", 
                           delay.getId(), delay.getRouteName(), delay.getReportTime());
            }
            return delays;
        } catch (Exception e) {
            logger.error("Error fetching delays", e);
            throw e;
        }
    }


    //handles post requests
    @PostMapping
    public BusDelay createDelay(@RequestBody BusDelay delay) {
        logger.info("POST /api/delays - Creating new delay: routeId={}, routeName={}, description={}", 
                   delay.getRouteId(), delay.getRouteName(), delay.getDescription());
        try {
            BusDelay savedDelay = service.saveDelay(delay);
            logger.info("Successfully saved delay with ID: {}", savedDelay.getId());
            return savedDelay;
        } catch (Exception e) {
            logger.error("Error saving delay", e);
            throw e;
        }
    }
}


