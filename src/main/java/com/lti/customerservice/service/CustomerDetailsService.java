package com.lti.customerservice.service;

import com.lti.customerservice.entity.CustomerDetails;


public interface CustomerDetailsService {
	public boolean saveCustomer(CustomerDetails customer);
	public CustomerDetails getCustomerById(Long id);
	public CustomerDetails getCustomerByName(String name);
	public CustomerDetails authenticateCustomer(CustomerDetails customer);
	public CustomerDetails getCustomerByUuid(String customerUuid);
}
