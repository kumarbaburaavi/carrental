package com.foo.carrental.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foo.carrental.bean.MessagesBean;
import com.foo.carrental.entity.Car;
import com.foo.carrental.repository.CarRepository;

@Service
public class CarService {

	@Autowired
	private MessagesBean messages;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private RentalService rentalService;

	/**
	 * @param mileage the minimum mileage to be searched for
	 * @return all cars that have a mileage greater than the given one
	 */
	public List<Car> findByMileageGreaterThan(Integer mileage) {
		return carRepository.findByMileageGreaterThan(mileage);
	}


	/**
	 * Saves the given car.
	 *
	 * @param car car to be saved
	 * @return the saved car coming from the database (only when no exceptions
	 *         occur)
	 * @throws EntityNotFoundException  if the provided station of the car does not
	 *                                  exist
	 * @throws EntityExistsException    if the given primary key already belongs to
	 *                                  an existing entity
	 * @throws IllegalArgumentException if the station is null
	 */
	public Car create(Car car) {
		if (carRepository.existsById(car.getRegistrationNr())) {
			throw new EntityExistsException(messages.get("carAlreadyExists"));
		}
		return carRepository.save(car);
	}

	/**
	 * Deletes a car with the given primary key. However, the car must be deletable.
	 * See {@link #canDelete(Car)}
	 *
	 * @param registrationNr the primary key to be searched for
	 * @throws EntityNotFoundException  if the given primary key does not belong to
	 *                                  any existing entity
	 * @throws IllegalArgumentException if the car cannot be deleted
	 */
	public void deleteById(String registrationNr) {
		Car car = carRepository.findById(registrationNr)
				.orElseThrow(() -> new EntityNotFoundException(messages.get("carNotFound")));
		if (!canDelete(car)) {
			throw new IllegalArgumentException(messages.get("carDeleteError"));
		}
		carRepository.delete(car);
	}

	/**
	 * Checks whether a given car can be deleted or not. The car can be deleted, if,
	 * and only if, the station car is not in use (station equals null) and the car
	 * was never used in any rental before.
	 *
	 * @param car the car to be checked
	 * @return true, if the car fulfills the mentioned criteria, false otherwise.
	 */
	public boolean canDelete(Car car) {
		return rentalService.findByCar(car).isEmpty();
	}

}
