package com.feemanagementapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feemanagementapp.dao.PaymentRepository;
import com.feemanagementapp.model.Payment;

@Component
public class PaymentService {

	@Autowired
	PaymentRepository paymentRepository;
	public int updatePaymentTransaction(Payment payment) throws Exception
	{
		Payment paid=paymentRepository.save(payment);
		if(paid!=null)
		{
			return 1;
		}
		else
		{
			throw new Exception("cannot process this transaction");
		}
	}
	
	
}
