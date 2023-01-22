package com.services.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.services.inventoryservice.entities.Product;


@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {

}
