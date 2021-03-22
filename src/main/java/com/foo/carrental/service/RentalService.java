package com.foo.carrental.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foo.carrental.bean.MessagesBean;
import com.foo.carrental.entity.Car;
import com.foo.carrental.entity.Customer;
import com.foo.carrental.entity.Rental;
import com.foo.carrental.repository.CarRepository;
import com.foo.carrental.repository.CustomerRepository;
import com.foo.carrental.repository.RentalRepository;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;
    
	@Autowired
	private MessagesBean messages;

	@Autowired
	private CarRepository carRepository;


    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Saves the given rental.
     * Sets following fields to null: id, km, return date, return station und the station of the car
     *
     * @param rental the rental to be saved
     * @return 
     */
    public Rental create(Rental rental) {
        rental.setId(null);
        rental.setKm(null);
        rental.setReturnDate(null);

		String registrationNr=rental.getCar().getRegistrationNr();
		Car car = carRepository.findById(registrationNr)
				.orElseThrow(() -> new EntityNotFoundException(messages.get("carNotFound")));
		rental.setCar(car);

		Integer customerNum=rental.getCustomer().getCustomerNumber();
		Customer customer  = customerRepository.findById(customerNum)
				.orElseThrow(() -> new EntityNotFoundException(messages.get("customerNotfound")));
		rental.setCustomer(customer);

       return rentalRepository.save(rental);
    }

    /**
     * Finishes the given rental.
     * Updates the record afterwards.
     *
     * @param rental           the rental to be updated
     * @return 
     */
    public Rental finish(Integer customerId, Integer km, Float hours, LocalDate returnDate) {
    	List<Rental> findRunningRentals = rentalRepository.findRunningRentals(customerId);
		if (findRunningRentals.isEmpty()) {
			throw new EntityNotFoundException(messages.get("rentalNotFound"));
		}
		
		Rental rental=findRunningRentals.get(0);
        rental.setKm(km);
        rental.getCar().setMileage(rental.getCar().getMileage() + rental.getKm());
        rental.setReturnDate(returnDate);
        return rentalRepository.save(rental);
    }

    /**
     * Returns a list of all running rentals.<br>
     * See {@link RentalRepository#findRunningRentals()}
     */
    public List<Rental> findRunningRentals() {
        return rentalRepository.findRunningRentals();
    }

    /**
     * Returns a list of all finished rentals.<br>
     * See {@link RentalRepository#findFinishedRentals()}
     */
    public List<Rental> findFinishedRentals() {
        return rentalRepository.findFinishedRentals();
    }
    
    /**
     * Returns a list of all rentals that have a specific car.<br>
     * See {@link RentalRepository#findByCar(Car)}
     */
    public List<Rental> findByCar(Car car) {
        return rentalRepository.findByCar(car);
    }

    /**
     * Returns a list of all rentals that have a specific customer.<br>
     * See {@link RentalRepository#findByCustomer(Car)}
     */
    public List<Rental> findByCustomer(Customer customer) {
        return rentalRepository.findByCustomer(customer);
    }
    /**
     * Checks whether a rental with a given id exists and can be finished.
     * If the id is null or the rental was not found, an empty {@link Optional} is returned.
     * If the rental was found and can be finished (see {@link #canFinish(Rental)}), then an {@link Optional} with the found rental is returned.
     */
    public Optional<Rental> existsAndCanFinish(Integer id) {
        if (id == null) {
            return Optional.empty();
        }
        Optional<Rental> opt = rentalRepository.findById(id);
        Rental rental;
        if (opt.isPresent() && canFinish((rental = opt.get()))) {
            return Optional.of(rental);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Checks whether a given rental can be finished or not.
     * Returns true if return date, km and return station fields are null, and false otherwise.
     */
    public boolean canFinish(Rental rental) {
        return rental.getReturnDate() == null && rental.getKm() == null;
    }


}
