package com.foo.carrental;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.foo.carrental.entity.Car;
import com.foo.carrental.entity.CarAvailability;
import com.foo.carrental.entity.Customer;
import com.foo.carrental.entity.Rental;
import com.foo.carrental.service.CarAvailService;
import com.foo.carrental.service.CarService;
import com.foo.carrental.service.CustomerService;
import com.foo.carrental.service.RentalService;

@SpringBootApplication
public class CarRentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(CustomerService customerService, CarService carService, RentalService rentalService,
			CarAvailService availService) {
		return args -> {

			loadCustomers(customerService);
			loadCars(carService);
			loadRental(rentalService);
			loadCarPool(availService);

		};
	}

	private void loadCustomers(CustomerService customerService) {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Customer>> typeReference = new TypeReference<List<Customer>>() {
		};

		InputStream inputStream = TypeReference.class.getResourceAsStream("/data/customers-data.json");
		try {
			List<Customer> customers = mapper.readValue(inputStream, typeReference);
			customers.forEach(customer -> {
				customerService.create(customer);
				System.out.println("Customer Saved!" + customer);
			});
		} catch (IOException e) {
			System.out.println("Unable to save Customer: " + e.getMessage());
		}
	}

	private void loadCars(CarService carService) {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Car>> typeReference = new TypeReference<List<Car>>() {
		};

		InputStream inputStream = TypeReference.class.getResourceAsStream("/data/cars-data.json");
		try {
			List<Car> cars = mapper.readValue(inputStream, typeReference);
			cars.forEach(car -> {
				carService.create(car);
				System.out.println("Car Saved!" + car);
			});
		} catch (IOException e) {
			System.out.println("Unable to save car: " + e.getMessage());
		}
	}

	private static void loadRental(RentalService rentalService) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

		TypeReference<List<Rental>> typeReference = new TypeReference<List<Rental>>() {
		};

		InputStream inputStream = TypeReference.class.getResourceAsStream("/data/carerental-data.json");
		try {
			List<Rental> rentals = mapper.readValue(inputStream, typeReference);
			rentals.forEach(rental -> {
				rentalService.create(rental);
				System.out.println("Rental Saved!" + rental);
			});
		} catch (IOException e) {
			System.out.println("Unable to save rental: " + e.getMessage());
		}
	}

	private static void loadCarPool(CarAvailService availService) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

		TypeReference<List<CarAvailability>> typeReference = new TypeReference<List<CarAvailability>>() {
		};

		InputStream inputStream = TypeReference.class.getResourceAsStream("/data/carpool-data.json");
		try {
			List<CarAvailability> availabilities = mapper.readValue(inputStream, typeReference);
			availabilities.forEach(avail -> {
				availService.create(avail);
				System.out.println("CarAvailability Saved!" + avail);
			});
		} catch (IOException e) {
			System.out.println("Unable to save CarAvailability: " + e.getMessage());
		}
	}
}
