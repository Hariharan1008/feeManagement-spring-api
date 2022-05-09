package com.feemanagementapp.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feemanagementapp.dao.ProductTransactionDao;
import com.feemanagementapp.dao.WalletLoginValidationDao;
import com.feemanagementapp.model.ProductTransaction;
import com.feemanagementapp.model.Products;
import com.feemanagementapp.service.VerifyWalletPointsAndUpdate;


@RestController
public class BuyProductsController {
  @GetMapping("procducts/verifyPoints")
  public String verifyWalletPoints(@RequestParam("productId") int productId,@RequestParam("mobile") long mobile) throws ClassNotFoundException, SQLException
  { 
	  VerifyWalletPointsAndUpdate points=new VerifyWalletPointsAndUpdate();
	  int verified=points.verifyPoints(productId,mobile);
	  if(verified==1)
	  {
		  return "valid";
	  }
	  else
	  {
		  return "insufficient points";
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
  public String updateWalletAndTransaction(@RequestParam("productId") int productId,@RequestParam("mobile") long mobile) throws ClassNotFoundException, SQLException
  {
	  VerifyWalletPointsAndUpdate updatePoints=new VerifyWalletPointsAndUpdate();
	   int updated=updatePoints.updateWalletPoints(productId, mobile);
	   if(updated==1)
	   {
		   ProductTransaction transaction=new ProductTransaction();
		   transaction.setProductId(productId);
		   transaction.setMobile(mobile);
		   transaction.setPurchasedOn(Date.valueOf(LocalDate.now()));
		   ProductTransactionDao updateTransaction=new ProductTransactionDao();
		   int completed=updateTransaction.updateProductTransaction(transaction);
		   if(completed==1)
		   {
			  return "Successfully Purchased";
		   }
		   else
		   {
			   return "Got some errors";
		   }
	   }
	   else
	   {
		   return "Got some errors";
	   }
  }
  
  
}
