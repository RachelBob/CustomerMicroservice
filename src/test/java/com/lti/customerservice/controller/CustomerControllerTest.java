package com.lti.customerservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lti.customerservice.entity.CustomerDetails;
import com.lti.customerservice.entity.CustomerDetailsDTO;
import com.lti.customerservice.service.CustomerDetailsService;
import com.lti.customerservice.utility.JWTUtil;

@SpringBootTest
public class CustomerControllerTest {

	@InjectMocks
	CustomerController controller;

	@Mock
	CustomerDetailsService service;
	
	@Mock
	JWTUtil jwtUtil;

	@Test
	public void addCustomerTest() {
		CustomerDetails customer = createCustomerStub();
		when(service.saveCustomer(customer)).thenReturn(true);
		ResponseEntity<Boolean> isSaved = controller.addCustomer(customer);
		assertTrue(isSaved.getBody());
		
	}

	@Test
	public void getCustomerByIdTest() {
		when(service.getCustomerById(11L)).thenReturn(createCustomerStub());
		ResponseEntity<CustomerDetails> response = controller.getCustomerByID(11L);
		assertEquals(response.getBody().getFirstName(), "Sneha12");
	}

	@Test
	public void getCustomerByNameTest() {
		when(service.getCustomerByName("Sneha12")).thenReturn(createCustomerStub());
		ResponseEntity<CustomerDetails> response = controller.getCustomerByName("Sneha12");
		assertEquals(response.getBody().getUsername(), "sp");
	}
	
	@Test
	public void getCustomerByUuidTest() {
		when(service.getCustomerByUuid("uuid")).thenReturn(createCustomerDTO());
		ResponseEntity<CustomerDetailsDTO> customerByUuid = controller.getCustomerByUuid("uuid");
		assertEquals(customerByUuid.getBody().getCustomerId(), 12L);
		
	}
	
	@Test
	public void  authenticateUserTest() {
		when(jwtUtil.generateToken()).thenReturn("token");
		when(service.authenticateCustomer(createCustomerStub())).thenReturn(createCustomerStub());
		ResponseEntity<CustomerDetails> authenticateUser = controller.authenticateUser(createCustomerStub());
		assertEquals(authenticateUser.getStatusCode(), HttpStatus.OK);
	}

	private CustomerDetails createCustomerStub() {

		CustomerDetails customer = new CustomerDetails(11L, "Sneha12", "Patil12", "sp", "sp@g.c", "abcd","aba");
		return customer;

	}
	
	private CustomerDetailsDTO createCustomerDTO() {
		CustomerDetailsDTO dto=new CustomerDetailsDTO(12L, "Sneha", "Patil", "spk", "s@g.c", "aa", "aa");
		return dto;
	}

}
