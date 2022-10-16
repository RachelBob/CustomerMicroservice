package com.lti.customerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomerExceptionContoller extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<Object> customerNotFoundException(CustomerNotFoundException exception ){
		ExceptionResponse response=new ExceptionResponse();
		response.setErrorCode(HttpStatus.NOT_FOUND.toString());
		response.setErrorMessage(exception.getMessage());
		return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomerAlreadyExistsException.class)
	public ResponseEntity<Object> customerAlreadyExistsException(CustomerAlreadyExistsException ex){
		ExceptionResponse response=new  ExceptionResponse();
		response.setErrorCode(HttpStatus.CONFLICT.toString());
		response.setErrorMessage("Customer is already exists");
		return new ResponseEntity<Object>(response, HttpStatus.CONFLICT);
		
	}
	
	@ExceptionHandler(CustomerAutenticationFailed.class)
	public ResponseEntity<Object> CustomerAutenticationFailed(CustomerAutenticationFailed ex){
		ExceptionResponse response=new  ExceptionResponse();
		response.setErrorCode(HttpStatus.UNAUTHORIZED.toString());
		response.setErrorMessage("Customer authentication failed. Please try with correct username and password");
		return new ResponseEntity<Object>(response, HttpStatus.UNAUTHORIZED);
		
	}

}
