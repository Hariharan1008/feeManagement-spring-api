package com.feemanagementapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feemanagementapp.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
	

}
