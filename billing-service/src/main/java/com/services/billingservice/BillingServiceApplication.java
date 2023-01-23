package com.services.billingservice;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.services.billingservice.entities.Bill;
import com.services.billingservice.entities.ProductItem;
import com.services.billingservice.model.Customer;
import com.services.billingservice.model.Product;
import com.services.billingservice.repository.BillRepository;
import com.services.billingservice.repository.ProductItemRepository;
import com.services.billingservice.services.CustomerRestClient;
import com.services.billingservice.services.ProductRestClient;


@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(
			BillRepository billRepository,
			ProductItemRepository productItemRepository,
			CustomerRestClient customerRestClient,
			ProductRestClient productRestClient
		)
	{
		return args -> {
			Collection<Product> products = productRestClient.allProducts().getContent();
			Long customerId =  1L;
			Customer customer = customerRestClient.findCustomerById(customerId);
			if (customer == null) throw new RuntimeException("Customer not found");
			Bill bill = new Bill();
			bill.setBillDate(new Date());
			bill.setCustomerId(customerId);
			Bill savedBill = billRepository.save(bill);
			products.forEach(product -> {
				ProductItem productItem = new ProductItem();
				productItem.setBill(savedBill);
				productItem.setProductId(product.getId());
				productItem.setQuantity(1 + new Random().nextInt(10));
				productItem.setPrice(product.getPrice());
				productItem.setDiscount(Math.random());
				productItemRepository.save(productItem);
				
			});
		};
	}

}
