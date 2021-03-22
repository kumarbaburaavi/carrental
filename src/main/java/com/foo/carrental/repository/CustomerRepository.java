package com.foo.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foo.carrental.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
