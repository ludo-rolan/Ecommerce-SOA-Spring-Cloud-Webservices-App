package com.services.billingservice.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.services.billingservice.model.Customer;


@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {
	
	@GetMapping(path = "/customers/{id}")
	Customer findCustomerById(@PathVariable Long id);

}
