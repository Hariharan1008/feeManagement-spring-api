package com.feemanagementapp.service;

import org.springframework.stereotype.Component;

import com.feemanagementapp.model.Products;
@Component
public class ProductValidator {
	public int validateProduct(Products product) throws Exception
	{
		int validName=verifyName(product.getName());
		int validPrice=verifyPrice(product.getPrice());
		int validImage=verifyImage(product.getImageUrl());
		int validPoints=verifyPoints(product.getWalletPoints());
		int validCategory=verifyCategory(product.getCategory());
		int validType=verifyType(product.getType());
		int validBrand=verifyBrand(product.getBrand());
		if(validName+validPrice+validImage+validPoints+validCategory+validType+validBrand==7)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	public static int verifyName(String name) throws Exception
	{
		if(name==null || name.trim().equals("") || name.trim().equals(" "))
		{
			throw new Exception("Name cannot be empty");
		}
		else
		{
			return 1;
		}
	}
	
	public static int verifyPrice(long price) throws Exception
	{
		if(price<=0)
		{
			throw new Exception("price cannot be empty ");
		}
		else
		{
			return 1;
		}
	}
	
	public static int verifyImage(String image) throws Exception
	{
		if(image==null || image.equals("") || image.equals(" ") )
		{
			throw new Exception("image cannot be empty ");
		}
		else if(!image.contains("."))
		{
			throw new Exception("please insert a extension like .jpg");
		}
		else
		{
			return 1;
		}
	}
	
	public static int verifyCategory(String category) throws Exception
	{
		if(category==null || category.equals("") || category.equals(" "))
		{
			throw new Exception("category cannot be empty ");
		}
		else
		{
			return 1;
		}
	}
	
	public static int verifyPoints(int points) throws Exception
	{
		if(points<=0)
		{
			throw new Exception("points cannot be empty ");
		}
		else
		{
			return 1;
		}
	}
	public static int verifyType(String type) throws Exception
	{
		if(type==null || type.equals("") || type.equals(" "))
		{
			throw new Exception("type cannot be empty ");
		}
		else
		{
			return 1;
		}
	}
	public static int verifyBrand(String brand) throws Exception
	{
		if(brand==null || brand.equals("") || brand.equals(" "))
		{
			throw new Exception("brand cannot be empty");
		}
		else
		{
			return 1;
		}
			
		
	}
	

}
