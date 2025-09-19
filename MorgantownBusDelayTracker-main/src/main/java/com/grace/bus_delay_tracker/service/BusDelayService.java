package com.grace.bus_delay_tracker.service;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.grace.bus_delay_tracker.model.BusDelay;
import com.grace.bus_delay_tracker.repository.BusDelayRepository;

@Service
public class BusDelayService {

    private final BusDelayRepository repository;

    //contructor injection to ensure repo field is always initialized
    public BusDelayService(BusDelayRepository repository) {
        this.repository = repository;
    }

    //retrieve the 5 most recent bus delay records from database, ordered by most recent first
    public List<BusDelay> getAllDelays(){
        return repository.findTop5ByOrderByReportTimeDesc();
    }

    //save a new delay record to database
    public BusDelay saveDelay(BusDelay delay){
        // Set the report time to current time if not already set
        if (delay.getReportTime() == null) {
            delay.setReportTime(LocalDateTime.now());
        }
        return repository.save(delay);
    }

}
