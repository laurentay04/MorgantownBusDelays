package com.grace.bus_delay_tracker.service;

import com.grace.bus_delay_tracker.model.Route;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * GTFSLoader: Reads GTFS routes.txt into Route entities
 */
public class GTFSLoader {

    /**
     * Load routes from a GTFS routes.txt file
     *
     * @param reader Reader pointing to routes.txt
     * @return list of Route entities
     * @throws IOException if file cannot be read
     */
    public List<Route> loadRoutes(Reader reader) throws IOException {
        List<Route> routes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(reader)) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                // skip header row
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                // split CSV by comma
                String[] record = line.split(",", -1); // -1 keeps empty fields

                if (record.length < 9) {
                    System.err.println("Skipping malformed line: " + line);
                    continue;
                }

                Route route = new Route();
                route.setRouteId(record[0]);            // route_id
                route.setRouteShortName(record[1]);     // route_short_name
                route.setRouteLongName(record[2]);      // route_long_name
                route.setRouteDesc(record[3]);          // route_desc
                route.setRouteUrl(record[4]);           // route_url
                route.setRouteColor(record[5]);         // route_color (hex string)
                route.setRouteTextColor(record[6]);     // route_text_color (hex string)

                // parse integers safely
                try {
                    route.setRouteType(Integer.parseInt(record[7]));
                } catch (NumberFormatException e) {
                    route.setRouteType(0); // fallback default
                }

                try {
                    route.setContinuousStops(Integer.parseInt(record[8]));
                } catch (NumberFormatException e) {
                    route.setContinuousStops(0);
                }

                routes.add(route);
            }
        }

        return routes;
    }
}
