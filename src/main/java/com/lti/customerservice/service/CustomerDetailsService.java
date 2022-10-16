package com.lti.customerservice.service;

import org.springframework.http.ResponseEntity;

import com.lti.customerservice.entity.CustomerDetails;


public interface CustomerDetailsService {
	public CustomerDetails saveCustomer(CustomerDetails customer);
	public CustomerDetails getCustomerById(Long id);
	public CustomerDetails getCustomerByName(String name);
	public CustomerDetails authenticateCustomer(CustomerDetails customer);
}
