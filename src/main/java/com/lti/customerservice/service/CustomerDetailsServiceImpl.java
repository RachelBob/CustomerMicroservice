package com.lti.customerservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lti.customerservice.entity.CustomerDetails;
import com.lti.customerservice.exception.CustomerAlreadyExistsException;
import com.lti.customerservice.exception.CustomerAutenticationFailed;
import com.lti.customerservice.exception.CustomerNotFoundException;
import com.lti.customerservice.repository.CustomerDetailsRepository;

@Service
public class CustomerDetailsServiceImpl implements CustomerDetailsService {

	@Autowired
	CustomerDetailsRepository customerRepository;
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	@Override
	public CustomerDetails saveCustomer(CustomerDetails customer) {
		CustomerDetails savedCustomer=null;
		savedCustomer=customerRepository.findByCustomerId(customer.getCustomerId());
		if(savedCustomer == null) {
			customer.setPassword(passwordEncoder.encode(customer.getPassword()));
			customerRepository.save(customer);
		}
		else
			throw new CustomerAlreadyExistsException();
		return savedCustomer;
	}

	@Override
	public CustomerDetails getCustomerById(Long id) {
		Optional<CustomerDetails> optionalObj1=Optional.ofNullable(customerRepository.findByCustomerId(id));
		CustomerDetails customer = optionalObj1.orElseThrow(()->new CustomerNotFoundException("Customer not found with id : "+id));
		return customer;
	}

	@Override
	public CustomerDetails getCustomerByName(String name) {
		Optional<CustomerDetails> optionalObj2=Optional.ofNullable(customerRepository.findByUsername(name));
		CustomerDetails customer = optionalObj2.orElseThrow(()->new CustomerNotFoundException("Customer not found with username : "+name));
		return customer;
	}

	@Override
	public CustomerDetails authenticateCustomer(CustomerDetails req) {
	    CustomerDetails	custObj = customerRepository.findByUsername(req.getUsername());
		if(!passwordEncoder.matches(req.getPassword(), custObj.getPassword()))
			throw new CustomerAutenticationFailed();
		
		custObj.setPassword("");
		return custObj;
	
	}

}
