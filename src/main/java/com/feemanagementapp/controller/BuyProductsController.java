package com.feemanagementapp.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feemanagementapp.dao.ProductTransactionDao;
import com.feemanagementapp.dao.WalletLoginValidationDao;
import com.feemanagementapp.model.Message;
import com.feemanagementapp.model.ProductTransaction;
import com.feemanagementapp.model.Products;
import com.feemanagementapp.service.ProductsService;
import com.feemanagementapp.service.VerifyWalletPointsAndUpdate;


@RestController
public class BuyProductsController {
	@Autowired
	ProductsService productService;
	
  @GetMapping("products/verifyPoints")
  public ResponseEntity<?> verifyWalletPoints(@RequestParam("productId") int productId,@RequestParam("mobile") long mobile) throws ClassNotFoundException, SQLException
  { 
	  try
	  {
		  int valid=productService.verifyWalletPointsAndProduct(productId, mobile);
		  if(valid==1)
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
  
  @GetMapping("products/verifyTpin")
  public String verfifyWalletPin(@RequestParam("mobile") long mobile,@RequestParam("tpin") int tPin) throws ClassNotFoundException, SQLException
  {
	   WalletLoginValidationDao login=new WalletLoginValidationDao();
	   int validCredential=login.validateWalletlogin(mobile, tPin);
	   if(validCredential==1)
	   {
		   return "valid";
	   }
	   else
	   {
		   return "invalid credentials";
	   }
  }
  
  @GetMapping("products/transaction/update")
  public ResponseEntity<?> updateWalletAndTransaction(@RequestParam("productId") int productId,@RequestParam("mobile") long mobile) throws ClassNotFoundException, SQLException
  {
	  try
	  {
		  int updated=productService.updateProductTransactions(productId, mobile);
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
