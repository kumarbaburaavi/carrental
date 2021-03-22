package com.foo.carrental.exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handle(ConstraintViolationException e) {
		ErrorResponse errors = new ErrorResponse();
		for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
			ErrorItem error = new ErrorItem();
			error.setCode(violation.getMessageTemplate());
			error.setMessage(violation.getMessage());
			errors.addError(error);
		}

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CarRentalException.class)
	public ResponseEntity<ErrorItem> handle(CarRentalException e) {
		ErrorItem error = new ErrorItem();
		error.setMessage(e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
		return handleException(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ IllegalArgumentException.class, EntityExistsException.class })
	public ResponseEntity<ErrorResponse> handleIllegalArgumentException(Exception ex, WebRequest request) {
		return handleException(ex, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleEntityNotFoundException(Exception ex, WebRequest request) {
		return handleException(ex, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity<ErrorResponse> handleException(Exception ex, HttpStatus httpStatus) {
		ErrorResponse errors = new ErrorResponse();
		ErrorItem error = new ErrorItem();
		error.setCode(httpStatus.getReasonPhrase());
		error.setMessage(ex.getMessage());
		errors.addError(error);
		return new ResponseEntity<>(errors, httpStatus);
	}

	public static class ErrorItem {
		@JsonInclude(JsonInclude.Include.NON_NULL)
		private String code;
		private String message;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}

	public static class ErrorResponse {

		private List<ErrorItem> errors = new ArrayList<>();

		public List<ErrorItem> getErrors() {
			return errors;
		}

		public void setErrors(List<ErrorItem> errors) {
			this.errors = errors;
		}

		public void addError(ErrorItem error) {
			this.errors.add(error);
		}

	}
}
