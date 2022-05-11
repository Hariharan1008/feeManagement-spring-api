package com.feemanagementapp.controller;


import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.feemanagementapp.model.Message;
import com.feemanagementapp.model.Wallet;
import com.feemanagementapp.service.NotificationService;
import com.feemanagementapp.service.PayFeesUsingWallet;
import com.feemanagementapp.service.WalletService;

@RestController
public class WalletController {
	
	@Autowired
	NotificationService notificationservice;
	
	@Autowired
	WalletService walletService;

	@GetMapping("wallet/addMoney")
  public ResponseEntity<?> addMoneyToWallet(@RequestParam("sessionMobile")long sessionMobile,@RequestParam("amount") long amount) throws ClassNotFoundException, SQLException
  {
	  try
	  {
		  int updated=walletService.updateMoneyToWallet(sessionMobile, amount);
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
  @GetMapping("wallet/findFeesAndWalletBalnce")
  public Wallet findFeesAdndWalletBalance(@RequestParam("email") String email,@RequestParam("mobile") long mobile) throws ClassNotFoundException, SQLException
  {
	  PayFeesUsingWallet pay=new PayFeesUsingWallet();
	  Wallet wallet=pay.findFeesAndWalletBalance(email, mobile);
	  return wallet;
  }
  @PostMapping("wallet/registration")
  public ResponseEntity<?> walletRegistration(@RequestBody Wallet wallet)
  {
	    try
	    {
	    	WalletService walletService=new WalletService();
	    	walletService.walletRegister(wallet);
	    	return new ResponseEntity<>(HttpStatus.OK);
	    }
	    catch(Exception e)
	    {
	    	Message message=new Message();
	    	message.setMessage(e.getMessage());
	    	return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
	    	
	    }
  }
  @PostMapping("wallet/verification")
  public ResponseEntity<?> verifyTpin(@RequestBody Wallet wallet ) throws ClassNotFoundException, SQLException
  {
	    try {
	    	  WalletService walletService=new WalletService();
	    	  int verified=walletService.walletVerification(wallet.getMobile(), wallet.getTpin());
	    	  if(verified==1)
	    	  {
	    		  return new ResponseEntity<>(HttpStatus.OK);
	    	  }
	    	  else
	    	  {
	    		  Message message=new Message();
	  	    	  message.setMessage("invalid pin");
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
