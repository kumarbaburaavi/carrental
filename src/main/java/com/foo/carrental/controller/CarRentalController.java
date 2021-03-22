package com.foo.carrental.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foo.carrental.entity.Car;
import com.foo.carrental.entity.CarAvailability;
import com.foo.carrental.entity.Customer;
import com.foo.carrental.entity.Rental;
import com.foo.carrental.service.CarAvailService;
import com.foo.carrental.service.CarService;
import com.foo.carrental.service.CustomerService;
import com.foo.carrental.service.RentalService;

@RestController
@RequestMapping(value = "car-rental/api/v1")
public class CarRentalController {

    @Autowired
    private CarService carService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RentalService rentalService; 
    @Autowired
    private CarAvailService availService;

    @GetMapping("running-rentals")
    public List<Rental> findRunningRentals() {
        return rentalService.findRunningRentals();
    }

    @GetMapping("mileage-greater-than/{mileage}")
    public List<Car> findByMileageGreaterThan(@PathVariable Integer mileage) {
        return carService.findByMileageGreaterThan(mileage);
    }

    @PostMapping("create-car")
    public ResponseEntity<Car> createCar(@RequestBody @Valid Car car) {
        return ResponseEntity.created(URI.create("")).body(carService.create(car));
    }

    @PostMapping("create-customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid Customer customer) {
        return ResponseEntity.created(URI.create("")).body(customerService.create(customer));
    }

    @DeleteMapping("delete-car/{registrationNr}")
    public void deleteCar(@PathVariable String registrationNr) {
        carService.deleteById(registrationNr);
    }
    
    @DeleteMapping("delete-customer/{customerId}")
    public void deleteCustomer(@PathVariable Integer customerId) {
        customerService.deleteById(customerId);
    }

    @PostMapping("book-rental")
    public ResponseEntity<Rental> bookRentals(@RequestBody @Valid Rental rental) {
        return ResponseEntity.created(URI.create("")).body(rentalService.create(rental));
    }
    
    @PostMapping("register-car-availability")
    public ResponseEntity<CarAvailability> registerCarAvailability(@RequestBody @Valid CarAvailability availability) {
        return ResponseEntity.created(URI.create("")).body(availService.create(availability));
    }
    
    @GetMapping("car-availability/{from}/{to}")
    public List<CarAvailability> findCarAvailability(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) {
        return availService.findCarsBetween(from, to);
    }
}
