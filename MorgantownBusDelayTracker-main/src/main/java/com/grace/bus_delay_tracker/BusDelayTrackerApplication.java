package com.grace.bus_delay_tracker;

import com.grace.bus_delay_tracker.model.Route;
import com.grace.bus_delay_tracker.repository.RouteRepository;
import com.grace.bus_delay_tracker.service.GTFSLoader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class BusDelayTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusDelayTrackerApplication.class, args);
	}

	/**
	 * CommandLineRunner executes after Spring Boot starts
	 */
	@Bean
	public CommandLineRunner loadData(RouteRepository routeRepository) {
		return args -> {
			try {
				//points to file path
				String filePath = "src/main/resources/routes.txt";

				GTFSLoader loader = new GTFSLoader();

				// Create a FileReader object from the file path
				FileReader fileReader = new FileReader(filePath);

				// loader now returns a List<Route>
				List<Route> routes = loader.loadRoutes(fileReader);

				//saves all routes to database
				routeRepository.saveAll(routes);

				System.out.println(" Saved " + routes.size() + " routes into the database");
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
	}
}