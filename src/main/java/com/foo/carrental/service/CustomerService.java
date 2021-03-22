package com.foo.carrental.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foo.carrental.bean.MessagesBean;
import com.foo.carrental.entity.Car;
import com.foo.carrental.entity.Customer;
import com.foo.carrental.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private MessagesBean messages;

    @Autowired
    private CustomerRepository customerRepository;

	@Autowired
	private RentalService rentalService;

    /**
     * @return a list of all customers
     */
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    /**
     * Saves the given customer.
     *
     * @param customer customer to be saved
     * @return the saved customer coming from the database (only if no exceptions occur)
     * @throws EntityExistsException if the given primary key already belongs to an existing entity
     */
    public Customer create(Customer customer) {
        if (customerRepository.existsById(customer.getCustomerNumber())) {
            throw new EntityExistsException(messages.get("customerAlreadyExists"));
        }
        return customerRepository.save(customer);
    }

    public boolean existsById(Integer id) {
        return customerRepository.existsById(id);
    }
    
	/**
	 * Deletes a car with the given primary key. However, the car must be deletable.
	 * See {@link #canDelete(Car)}
	 *
	 * @param customerId the primary key to be searched for
	 * @throws EntityNotFoundException  if the given primary key does not belong to
	 *                                  any existing entity
	 * @throws IllegalArgumentException if the car cannot be deleted
	 */
	public void deleteById(Integer customerId) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new EntityNotFoundException(messages.get("customerNotFound")));
		if (!canDelete(customer)) {
			throw new IllegalArgumentException(messages.get("carDeleteError"));
		}
		customerRepository.delete(customer);
	}

	/**
	 * Checks whether a given car can be deleted or not. The car can be deleted, if,
	 * and only if, the station car is not in use (station equals null) and the car
	 * was never used in any rental before.
	 *
	 * @param car the car to be checked
	 * @return true, if the car fulfills the mentioned criteria, false otherwise.
	 */
	public boolean canDelete(Customer customer) {
		return rentalService.findByCustomer(customer).isEmpty();
	}
}
