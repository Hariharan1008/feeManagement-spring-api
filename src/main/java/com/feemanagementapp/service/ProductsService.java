package com.feemanagementapp.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feemanagementapp.dao.ProductRepository;
import com.feemanagementapp.dao.ProductTransactionDao;
import com.feemanagementapp.model.ProductTransaction;
import com.feemanagementapp.model.Products;
@Component
public class ProductsService {
	@Autowired
	ProductRepository productRepository;
	
	public String insertProduct(Products product) 
	{
		try {
			ProductValidator validator=new ProductValidator();
			 int validated=validator.validateProduct(product);
			 if(validated==1)
			 {
			   productRepository.save(product);
			   return "success";
			 }
			 else
			 {
				return "failed";
			 }
		}
		 catch(Exception e) {
			 return e.getMessage();
		 }
		
	}
	
	public List<Products> getAllProducts() throws Exception 
	{
		List<Products> products=productRepository.findAll();
		if(products!=null)
		{
		  return products;
		}
		else
		{
			throw new Exception("no records found");
		}
	}
	
	public Products getProductUsingId(int id) throws Exception
	{
		  Products product=productRepository.getProductById(id);
		  if(product!=null)
		  {
		    return product;
		  }
		  else
		  {
			  throw new Exception("no record found");
		  }
	}
	
	public int verifyWalletPointsAndProduct(int id,long mobile) throws Exception
	{
		VerifyWalletPointsAndUpdate points=new VerifyWalletPointsAndUpdate();
		  int verified=points.verifyPoints(id,mobile);
		  if(verified==1)
		  {
			  return 1;
		  }
		  else
		  {
			  throw new Exception("Insufficient points");
		  }
	}
	
	public int updateProductTransactions(int productId,long mobile) throws Exception
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
				  return 1;
			   }
			   else
			   {
				   throw new Exception("cant process your request at this time");
			   }
		   }
		   else
		   {
			   throw new Exception("cant process your request at this time");
		   }
	}

}
