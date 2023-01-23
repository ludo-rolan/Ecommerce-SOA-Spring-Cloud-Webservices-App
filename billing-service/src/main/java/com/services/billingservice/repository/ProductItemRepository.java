package com.services.billingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.services.billingservice.entities.ProductItem;



public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {

}
