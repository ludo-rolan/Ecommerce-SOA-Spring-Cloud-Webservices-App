package com.services.billingservice.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.services.billingservice.model.Product;


@FeignClient(name = "INVENTORY-SERVICE")
public interface ProductRestClient {
	
	@GetMapping(path = "/products/{id}")
	Product findProductById(@PathVariable Long id);
	@GetMapping(path = "/products")
	PagedModel<Product> allProducts();

}
