package com.services.inventoryservice;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.services.inventoryservice.entities.Product;
import com.services.inventoryservice.repository.ProductRepository;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	
	
	@Bean
	CommandLineRunner commandLineRunner (ProductRepository productRepository,
			RepositoryRestConfiguration repositoryRestConfiguration)
	{
		repositoryRestConfiguration.exposeIdsFor(Product.class);
		
		return args -> {
			productRepository.saveAll(
				List.of(
					Product.builder().name("Computer").quantity(12).price(18000).build(),
					Product.builder().name("Printer").quantity(120).price(8000).build(),
					Product.builder().name("Smartphone").quantity(31).price(15000).build()
				)
			);
		};
	}

}
