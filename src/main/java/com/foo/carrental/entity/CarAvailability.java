package com.foo.carrental.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class CarAvailability implements Serializable {

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
	
	@NotNull(message = "{notBlank}")
	@Range(min = 10, max = 999_999, message = "{hourlyRateError}")
	private Float hourlyrate;
	
	@ManyToOne
	@NotNull(message = "{notNull}")
	private Car car;

	public CarAvailability() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param id
	 * @param rentalDate
	 * @param returnDate
	 * @param hourlyrate
	 * @param car
	 */
	public CarAvailability(Integer id,
			@FutureOrPresent(message = "{futOrPres}") @NotNull(message = "{notNull}") LocalDate rentalDate,
			@FutureOrPresent(message = "{futOrPres}") LocalDate returnDate,
			@NotNull(message = "{notBlank}") @Range(min = 10, max = 999999, message = "{hourlyRateError}") Float hourlyrate,
			@NotNull(message = "{notNull}") Car car) {
		super();
		this.id = id;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
		this.hourlyrate = hourlyrate;
		this.car = car;
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
	 * @return the hourlyrate
	 */
	public Float getHourlyrate() {
		return hourlyrate;
	}

	/**
	 * @param hourlyrate the hourlyrate to set
	 */
	public void setHourlyrate(Float hourlyrate) {
		this.hourlyrate = hourlyrate;
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

	@Override
	public int hashCode() {
		return Objects.hash(car, hourlyrate, id, rentalDate, returnDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof CarAvailability)) {
			return false;
		}
		CarAvailability other = (CarAvailability) obj;
		return Objects.equals(car, other.car) && Objects.equals(hourlyrate, other.hourlyrate)
				&& Objects.equals(id, other.id) && Objects.equals(rentalDate, other.rentalDate)
				&& Objects.equals(returnDate, other.returnDate);
	}

	@Override
	public String toString() {
		return "CarAvailability [id=" + id + ", rentalDate=" + rentalDate + ", returnDate=" + returnDate
				+ ", hourlyrate=" + hourlyrate + ", car=" + car + "]";
	}

	
}
