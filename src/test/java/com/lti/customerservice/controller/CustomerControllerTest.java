package com.lti.customerservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.lti.customerservice.entity.CustomerDetails;
import com.lti.customerservice.service.CustomerDetailsService;

@SpringBootTest
public class CustomerControllerTest {

	@InjectMocks
	CustomerController controller;

	@Mock
	CustomerDetailsService service;

	@Test
	public void addCustomer() {
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
	public void getCustomerByName() {
		when(service.getCustomerByName("Sneha12")).thenReturn(createCustomerStub());
		ResponseEntity<CustomerDetails> response = controller.getCustomerByName("Sneha12");
		assertEquals(response.getBody().getUsername(), "sp");
	}

	private CustomerDetails createCustomerStub() {

		CustomerDetails customer = new CustomerDetails(11L, "Sneha12", "Patil12", "sp", "sp@g.c", "abcd","aba");
		return customer;

	}

}
