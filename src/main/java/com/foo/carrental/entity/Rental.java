package com.foo.carrental.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Rental implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@FutureOrPresent(message = "{futOrPres}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "{notNull}")
	private LocalDate rentalDate;

	@FutureOrPresent(message = "{futOrPres}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate returnDate;

	@Column(name = "distance")
	private Integer km;

	@Column(name = "hours")
	private Float hours;

	@ManyToOne
	@NotNull(message = "{notNull}")
	private Customer customer;

	@ManyToOne
	@NotNull(message = "{notNull}")
	private Car car;

	public Rental() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param rentalDate
	 * @param returnDate
	 * @param km
	 * @param customer
	 * @param car
	 * @param rentalStation
	 * @param returnStation
	 */
	public Rental(Integer id,
			@FutureOrPresent(message = "{futOrPres}") @NotNull(message = "{notNull}") LocalDate rentalDate,
			@FutureOrPresent(message = "{futOrPres}") @NotNull(message = "{notNull}") LocalDate returnDate, Integer km,
			Float hours, @NotNull(message = "{notNull}") Customer customer, @NotNull(message = "{notNull}") Car car) {
		super();
		this.id = id;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
		this.km = km;
		this.customer = customer;
		this.car = car;
		this.hours = hours;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the rentalDate
	 */
	public LocalDate getRentalDate() {
		return rentalDate;
	}

	/**
	 * @param rentalDate the rentalDate to set
	 */
	public void setRentalDate(LocalDate rentalDate) {
		this.rentalDate = rentalDate;
	}

	/**
	 * @return the returnDate
	 */
	public LocalDate getReturnDate() {
		return returnDate;
	}

	/**
	 * @param returnDate the returnDate to set
	 */
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	/**
	 * @return the km
	 */
	public Integer getKm() {
		return km;
	}

	/**
	 * @param km the km to set
	 */
	public void setKm(Integer km) {
		this.km = km;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the car
	 */
	public Car getCar() {
		return car;
	}

	/**
	 * @param car the car to set
	 */
	public void setCar(Car car) {
		this.car = car;
	}

	/**
	 * @return the hours
	 */
	public Float getHours() {
		return hours;
	}

	/**
	 * @param hours the hours to set
	 */
	public void setHours(Float hours) {
		this.hours = hours;
	}

	@Override
	public int hashCode() {
		return Objects.hash(car, customer, id, km, rentalDate, returnDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Rental)) {
			return false;
		}
		Rental other = (Rental) obj;
		return Objects.equals(car, other.car) && Objects.equals(customer, other.customer) && Objects.equals(id, other.id)
				&& Objects.equals(km, other.km) && Objects.equals(rentalDate, other.rentalDate)
				&& Objects.equals(returnDate, other.returnDate);
	}

	@Override
	public String toString() {
		return "Rental [id=" + id + ", rentalDate=" + rentalDate + ", returnDate=" + returnDate + ", km=" + km
				+ ", customer=" + customer + ", car=" + car + "]";
	}

}
