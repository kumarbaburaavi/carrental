package com.foo.carrental.service;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foo.carrental.bean.MessagesBean;
import com.foo.carrental.entity.Car;
import com.foo.carrental.entity.CarAvailability;
import com.foo.carrental.repository.CarAvailRepository;
import com.foo.carrental.repository.CarRepository;

@Service
public class CarAvailService {

	@Autowired
	private CarAvailRepository availRepository;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private MessagesBean messages;

	/**
	 * @param from
	 * @param to
	 * @return
	 */
	public List<CarAvailability> findCarsBetween(LocalDate from, LocalDate to) {
		return availRepository.findCarByDate(from, to);
	}

	/**
	 * @return a list of all availabilities
	 */
	public List<CarAvailability> findAll() {
		return availRepository.findAll();
	}

	/**
	 * Saves the given availability.
	 *
	 * @param availability availability to be saved
	 * @return the saved availability coming from the database (only if no
	 *         exceptions occur)
	 * @throws EntityExistsException if the given primary key already belongs to an
	 *                               existing entity
	 */
	public CarAvailability create(CarAvailability availability) {
		
		String registrationNr=availability.getCar().getRegistrationNr();
		Car car = carRepository.findById(registrationNr)
				.orElseThrow(() -> new EntityNotFoundException(messages.get("carNotFound")));
		availability.setCar(car);
		return availRepository.save(availability);
	}

}
