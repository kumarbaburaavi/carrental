package com.foo.carrental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foo.carrental.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {
	List<Car> findByMileageGreaterThan(Integer mileage);
}
