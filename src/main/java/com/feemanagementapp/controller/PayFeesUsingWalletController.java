package com.feemanagementapp.controller;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feemanagementapp.model.Message;
import com.feemanagementapp.model.Wallet;
import com.feemanagementapp.service.PayFeesUsingWallet;
import com.feemanagementapp.service.SubtractMoneyFromWallet;
import com.feemanagementapp.service.UpdateFeesAfterUsingWallet;
import com.feemanagementapp.service.WalletService;



@RestController
public class PayFeesUsingWalletController {

	@GetMapping("fees/pay/wallet/getBalanceAndFees")
	public Wallet getPaidFeesAndWalletBalance(@RequestParam("email") String email,@RequestParam("mobile") long mobile) throws ClassNotFoundException, SQLException
	{
		PayFeesUsingWallet pay=new PayFeesUsingWallet();
		Wallet wallet=pay.findFeesAndWalletBalance(email, mobile);
		return wallet;
	}
	@GetMapping("fees/pay/wallet/update")
	public ResponseEntity<?> updateFeesAndWallet(@RequestParam("email") String email,@RequestParam("mobile") long mobile,@RequestParam("amount") long amount) throws Exception
	{ 
		try {
			WalletService walletService=new WalletService();
			int updated=walletService.payFeesAndUpdateUsingWallet(email, mobile, amount);
			if(updated==1)
			{
				return new ResponseEntity<>(HttpStatus.OK);
			}
			 else
	    	  {
	    		  Message message=new Message();
	  	    	  message.setMessage("cant process your request this time");
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
