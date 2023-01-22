package com.services.customerservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.services.customerservice.entities.Customer;


@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
