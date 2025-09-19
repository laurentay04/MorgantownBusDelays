package com.grace.bus_delay_tracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grace.bus_delay_tracker.model.BusDelay;

//spring bean / DOA declaration
@Repository
public interface BusDelayRepository extends JpaRepository<BusDelay, Long> {
    // Find all delays ordered by report time descending (most recent first)
    List<BusDelay> findAllByOrderByReportTimeDesc();
    
    // Find top 5 delays ordered by report time descending (most recent first)
    List<BusDelay> findTop5ByOrderByReportTimeDesc();
}
