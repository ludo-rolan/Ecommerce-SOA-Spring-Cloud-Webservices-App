package com.services.billingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.services.billingservice.entities.Bill;



public interface BillRepository extends JpaRepository<Bill, Long> {

}
