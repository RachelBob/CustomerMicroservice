package com.lti.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lti.customerservice.entity.CustomerDetails;

@Repository
public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, Long> {
	public CustomerDetails findByUsername(String username);
	public CustomerDetails findByCustomerId(Long customerId);
	public CustomerDetails findByCustomerUuid(String customerUuid);
}
