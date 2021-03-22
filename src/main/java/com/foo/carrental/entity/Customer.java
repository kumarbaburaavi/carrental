package com.foo.carrental.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity

public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Range(min = 1, max = 999_999, message = "{customerNumberRange}")
	@NotNull(message = "{notNull}")
	private Integer customerNumber;

	@Size(min = 1, max = 255, message = "{nameRange}")
	@NotNull(message = "{notNull}")
	private String lastName;

	@Size(min = 1, max = 255, message = "{nameRange}")
	@NotNull(message = "{notNull}")
	private String firstName;

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param customerNumber
	 * @param lastName
	 * @param firstName
	 */
	public Customer(
			@Range(min = 111111, max = 999999, message = "{customerNumberRange}") @NotNull(message = "{notNull}") Integer customerNumber,
			@Size(min = 1, max = 255, message = "{nameRange}") @NotNull(message = "{notNull}") String lastName,
			@Size(min = 1, max = 255, message = "{nameRange}") @NotNull(message = "{notNull}") String firstName) {
		super();
		this.customerNumber = customerNumber;
		this.lastName = lastName;
		this.firstName = firstName;
	}

	/**
	 * @return the customerNumber
	 */
	public Integer getCustomerNumber() {
		return customerNumber;
	}

	/**
	 * @param customerNumber the customerNumber to set
	 */
	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName.strip();
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName.strip();
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerNumber, firstName, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Customer)) {
			return false;
		}
		Customer other = (Customer) obj;
		return Objects.equals(customerNumber, other.customerNumber) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName);
	}

	@Override
	public String toString() {
		return "(" + customerNumber + ") " + lastName.toUpperCase() + ' ' + firstName;
	}
}
