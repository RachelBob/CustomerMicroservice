package com.lti.customerservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.customerservice.entity.CustomerDetails;
import com.lti.customerservice.service.CustomerDetailsService;
import com.lti.customerservice.utility.JWTUtil;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
	
	Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerDetailsService customerService;
	
	@Autowired
	JWTUtil jwtUtil;

	/*
	 * @GetMapping("/test") public String test() { return "customer service"; }
	 */

	//http://localhost:8080/register-customer
	@PostMapping("/register-customer")
	public ResponseEntity<CustomerDetails> addCustomer(@RequestBody CustomerDetails customer) {
		logger.info("In customer controller -> addCustomer method : Request {}  " , customer);
		CustomerDetails savedCustomer = customerService.saveCustomer(customer);
		logger.info("Response:uuid {} ",savedCustomer.getCustomerUuid());
		return new ResponseEntity<CustomerDetails>(savedCustomer, HttpStatus.CREATED);
	}

	//http://localhost:8080/customers/1
	@GetMapping("/id/{id}")
	public ResponseEntity<CustomerDetails> getCustomerByID(@PathVariable(value = "id") Long id) {
		logger.info("In Customer Controller -> getCustomerById method : ID {} ",id);
		CustomerDetails customer = customerService.getCustomerById(id);
		logger.info("Response:uuid {} ",customer.getCustomerUuid());
		return new ResponseEntity<CustomerDetails>(customer, HttpStatus.OK);
		
	}

	//http://localhost:8080/customers?username=snehap
	@GetMapping("/getbyname")
	public ResponseEntity<CustomerDetails> getCustomerByName(@RequestParam(value = "username") String username) {
		logger.info("Customer Controller :: getCustomerByName : username {} ",username);
		CustomerDetails customer = customerService.getCustomerByName(username);
		logger.info("Response :: uuid  {} " , customer.getCustomerUuid());
		return new ResponseEntity<CustomerDetails>(customer, HttpStatus.OK);
		
	}
	
	@GetMapping("/uuid/{uuid}")
	public ResponseEntity<CustomerDetails> getCustomerByUuid(@PathVariable(value="uuid") String uuid){
		logger.info("Customer Controller :: getCustomerByUuid : uuid {} ",uuid);
		CustomerDetails customerByUuid = customerService.getCustomerByUuid(uuid);
		return new ResponseEntity<CustomerDetails>(customerByUuid, HttpStatus.OK);
	}
	
	@PostMapping("/authenticate_user")
	public ResponseEntity<CustomerDetails> authenticateUser(@RequestBody CustomerDetails req) {
		 String jwtToken = jwtUtil.generateToken();
		 HttpHeaders responseHeaders = new HttpHeaders();
		 responseHeaders.set("jwtToken", jwtToken);
		 return new ResponseEntity<CustomerDetails>(customerService.authenticateCustomer(req), responseHeaders, org.springframework.http.HttpStatus.OK);
	}
}
