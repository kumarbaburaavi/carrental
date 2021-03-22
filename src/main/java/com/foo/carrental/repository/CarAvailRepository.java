package com.foo.carrental.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.foo.carrental.entity.CarAvailability;

@Repository
public interface CarAvailRepository extends JpaRepository<CarAvailability, Integer> {

	@Query("SELECT c FROM CarAvailability c WHERE c.rentalDate BETWEEN :from AND :to ")
	List<CarAvailability> findCarByDate(@Param("from") LocalDate from, @Param("to") LocalDate to);

}
