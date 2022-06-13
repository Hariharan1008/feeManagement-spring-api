package com.feemanagementapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.feemanagementapp.model.Message;
import com.feemanagementapp.model.Payment;
import com.feemanagementapp.service.PaymentService;

@RestController
public class PaymentController {
	 @Autowired
	 PaymentService paymentSevice;
	 
	@PostMapping("payment/card/update")
	public ResponseEntity<?> updatePaymentTransaction(@RequestBody Payment payment)
	{
		try
		{
			int updated=paymentSevice.updatePaymentTransaction(payment);
			if(updated==1)
			{
				  return new ResponseEntity<>(HttpStatus.OK);
			  }
			  else
			  {
				  Message message=new Message();
				  message.setMessage("can not process your request this time");
				  return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
			  } 
		  }
		  catch(Exception e)
		  {
			  Message message=new Message();
			  message.setMessage(e.getMessage());
			  return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		  }
	}

}
