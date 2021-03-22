package com.foo.carrental.exceptions;

public class CarRentalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CarRentalException() {
		super();
	}

	public CarRentalException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public CarRentalException(final String message) {
		super(message);
	}

	public CarRentalException(final Throwable cause) {
		super(cause);
	}
}
