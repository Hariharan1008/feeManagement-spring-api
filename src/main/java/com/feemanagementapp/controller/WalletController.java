package com.feemanagementapp.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feemanagementapp.dao.AddMoneyToWallet;
import com.feemanagementapp.dao.WalletInsertionDao;
import com.feemanagementapp.dao.WalletLoginValidationDao;
import com.feemanagementapp.model.Wallet;
import com.feemanagementapp.service.NotificationService;
import com.feemanagementapp.service.PayFeesUsingWallet;

@RestController
public class WalletController {
	
	@Autowired
	NotificationService notificationservice;

	@GetMapping("wallet/addMoney")
  public String addMoneyToWallet(@RequestParam("sessionMobile")long sessionMobile,@RequestParam("amount") long amount) throws ClassNotFoundException, SQLException
  {
		AddMoneyToWallet add=new AddMoneyToWallet();
		int completed=0;
		completed = add.addMoney(sessionMobile, amount);
		String message=amount+"rs "+"added successfully to your wallet";
		if(completed==1)
		{
			notificationservice.notificationInserter(message, sessionMobile);
		    return "money add Successfully";
		}
		else
		{
			return "money not added";
		}
		
  }
  @GetMapping("wallet/findFeesAndWalletBalnce")
  public Wallet findFeesAdndWalletBalance(@RequestParam("email") String email,@RequestParam("mobile") long mobile) throws ClassNotFoundException, SQLException
  {
	  PayFeesUsingWallet pay=new PayFeesUsingWallet();
	  Wallet wallet=pay.findFeesAndWalletBalance(email, mobile);
	  return wallet;
  }
  @GetMapping("wallet/registration")
  public String walletRegistration(@RequestParam("name") String name,@RequestParam("mobile") long mobile,@RequestParam("tpin") int tpin) throws ClassNotFoundException, SQLException
  {
	    long balance=0;
		Wallet wallet=new Wallet();
		wallet.setName(name);
		wallet.setMobile(mobile);
		wallet.setBalance(balance);
		wallet.setTpin(tpin);
		wallet.setWalletPoints(10);
		WalletInsertionDao insert=new WalletInsertionDao();
		int completed=0;
		String result="";
		completed=insert.walletInsertion(wallet);
		if(completed==0)
		{
			result="Creation failed";
		}
		else
		{
			result="Success";
		}
		return result;
  }
  @GetMapping("wallet/verification")
  public String verifyTpin(@RequestParam("tpin") int tpin,@RequestParam("mobile") long mobile) throws ClassNotFoundException, SQLException
  {
	    WalletLoginValidationDao login=new WalletLoginValidationDao();
		String message=null;
		int valid=login.validateWalletlogin(mobile,tpin);
		if(valid==1)
		{
			message="valid";
		}
		else
		{
			message="invalid";
		}
		return message;
  }
}
