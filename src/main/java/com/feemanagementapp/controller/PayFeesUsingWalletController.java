package com.feemanagementapp.controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feemanagementapp.model.Wallet;
import com.feemanagementapp.service.PayFeesUsingWallet;
import com.feemanagementapp.service.SubtractMoneyFromWallet;
import com.feemanagementapp.service.UpdateFeesAfterUsingWallet;



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
	public String updateFeesAndWallet(@RequestParam("email") String email,@RequestParam("mobile") long mobile,@RequestParam("amount") long amount) throws Exception
	{
		PayFeesUsingWallet pay=new PayFeesUsingWallet();
		int amountValid=0;
		amountValid = pay.amountVerifier(amount,mobile,email);
		String message=null;
		if(amountValid==0)
		{	
			UpdateFeesAfterUsingWallet updatefees=new UpdateFeesAfterUsingWallet();
			updatefees.adminTableUpdation(amount,email);
			SubtractMoneyFromWallet subtract=new SubtractMoneyFromWallet();		
			subtract.walletUpdatiorAfterPayment( mobile, amount);
			System.out.println(mobile+" "+amount);
		    message="success";
		}
		else
		{
			 message="Insufficient funds";
			
		}
		return message;
	}
}
