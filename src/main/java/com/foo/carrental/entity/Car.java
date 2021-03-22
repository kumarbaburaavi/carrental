package com.foo.carrental.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
public class Car implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@NotBlank(message = "{notBlank}")
	private String registrationNr;

	@NotNull(message = "{notNull}")
	@Range(min = 1850, max = 2090, message = "{constructionYearError}")
	private Integer constructionYear;

	@NotNull(message = "{notNull}")
	@Min(value = 0, message = "{mileageError}")
	private Integer mileage;

	@NotBlank(message = "{notBlank}")
	private String model;

	public Car() {
	}

	/**
	 * @param registrationNr
	 * @param constructionYear
	 * @param mileage
	 * @param model
	 * @param station
	 */
	public Car(@NotBlank(message = "{notBlank}") String registrationNr,
			@NotNull(message = "{notNull}") @Range(min = 1850, max = 2090, message = "{constructionYearError}") Integer constructionYear,
			@NotNull(message = "{notNull}") @Min(value = 0, message = "{mileageError}") Integer mileage,
			@NotBlank(message = "{notBlank}") String model) {
		super();
		this.registrationNr = registrationNr;
		this.constructionYear = constructionYear;
		this.mileage = mileage;
		this.model = model;
	}

	public void setRegistrationNr(String registrationNr) {
		this.registrationNr = registrationNr.strip();
	}

	public void setModel(String model) {
		this.model = model.strip();
	}

	/**
	 * @return the constructionYear
	 */
	public Integer getConstructionYear() {
		return constructionYear;
	}

	/**
	 * @param constructionYear the constructionYear to set
	 */
	public void setConstructionYear(Integer constructionYear) {
		this.constructionYear = constructionYear;
	}

	/**
	 * @return the mileage
	 */
	public Integer getMileage() {
		return mileage;
	}

	/**
	 * @param mileage the mileage to set
	 */
	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	/**
	 * @return the registrationNr
	 */
	public String getRegistrationNr() {
		return registrationNr;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	@Override
	public int hashCode() {
		return Objects.hash(constructionYear, mileage, model, registrationNr);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Car)) {
			return false;
		}
		Car other = (Car) obj;
		return Objects.equals(constructionYear, other.constructionYear) && Objects.equals(mileage, other.mileage)
				&& Objects.equals(model, other.model) && Objects.equals(registrationNr, other.registrationNr);
	}

	@Override
	public String toString() {
		return "Car [registrationNr=" + registrationNr + ", constructionYear=" + constructionYear + ", mileage="
				+ mileage + ", model=" + model +  "]";
	}
	
}
