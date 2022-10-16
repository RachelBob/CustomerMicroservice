package com.lti.customerservice.exception;

public class CustomerAutenticationFailed extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public CustomerAutenticationFailed() {
		super();
	}
	
	public CustomerAutenticationFailed(String message) {
		super(message);
	}
}
