package com.feemanagementapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feemanagementapp.dao.ProductRepository;
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
	
	public List<Products> getAllProducts() 
	{
		List<Products> products=productRepository.findAll();
		return products;
	}
	
	public Products getProductUsingId(int id)
	{
		  Products product=productRepository.getProductById(id);
		  return product;
	}

}
