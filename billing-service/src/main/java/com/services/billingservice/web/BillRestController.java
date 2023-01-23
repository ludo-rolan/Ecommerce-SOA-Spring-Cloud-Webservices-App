package com.services.billingservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.services.billingservice.entities.Bill;
import com.services.billingservice.repository.BillRepository;
import com.services.billingservice.repository.ProductItemRepository;
import com.services.billingservice.services.CustomerRestClient;
import com.services.billingservice.services.ProductRestClient;


@RestController
public class BillRestController {
	
	@Autowired
	private BillRepository billRepository;
	@Autowired
	private ProductItemRepository productItemRepository;
	@Autowired
	private CustomerRestClient customerRestClient;
	@Autowired
	private ProductRestClient productRestClient;
	
	
	@GetMapping("/fullBill/{id}")
	public Bill bill(@PathVariable Long id)
	{
		Bill bill =  billRepository.findById(id).get();
		bill.setCustomer(customerRestClient.findCustomerById(bill.getCustomerId()));
		bill.getProductItems().forEach(pi -> {
			pi.setProduct(productRestClient.findProductById(pi.getProductId()));
		});
		
		return bill;
	}

}
