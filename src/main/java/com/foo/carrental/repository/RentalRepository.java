package com.foo.carrental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.foo.carrental.entity.Car;
import com.foo.carrental.entity.Customer;
import com.foo.carrental.entity.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

	@Query("SELECT r FROM Rental r WHERE r.km IS NULL AND r.returnDate IS NULL ")
	List<Rental> findRunningRentals();

	@Query("SELECT r FROM Rental r WHERE r.km IS NOT NULL AND r.returnDate IS NOT NULL ")
	List<Rental> findFinishedRentals();

	List<Rental> findByCar(Car car);

	List<Rental> findByCustomer(Customer customer);

}
