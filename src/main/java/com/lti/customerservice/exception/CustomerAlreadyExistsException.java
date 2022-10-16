package com.lti.customerservice.exception;

public class CustomerAlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CustomerAlreadyExistsException() {
		super();
	}
	
	public CustomerAlreadyExistsException(String message) {
		super(message);
	}
	
}
