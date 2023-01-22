package com.services.customerservice;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.services.customerservice.entities.Customer;
import com.services.customerservice.repositories.CustomerRepository;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner (CustomerRepository customerRepository,
			RepositoryRestConfiguration repositoryRestConfiguration)
	{
		return args -> {
			repositoryRestConfiguration.exposeIdsFor(Customer.class);
			
			customerRepository.saveAll(
				List.of(
					Customer.builder().name("Hassan").email("hassan@gmail.com").build(),
					Customer.builder().name("Hanane").email("hanane@gmail.com").build(),
					Customer.builder().name("Imane").email("imane@gmail.com").build()
				)
			);
			
			customerRepository.findAll().forEach(c -> {
				System.out.println(c);
			});
		};
	}

}
