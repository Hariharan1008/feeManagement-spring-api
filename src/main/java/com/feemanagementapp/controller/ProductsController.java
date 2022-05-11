package com.feemanagementapp.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feemanagementapp.dao.GetAllProdutsDao;
import com.feemanagementapp.dao.ProductRepository;
import com.feemanagementapp.model.Message;
import com.feemanagementapp.model.Products;
import com.feemanagementapp.service.ProductValidator;
import com.feemanagementapp.service.ProductsService;

@RestController
public class ProductsController {
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductsService productService;
	
	@Autowired
	ProductValidator productValidator;
	
	
	@PostMapping("products/insert")
	public ResponseEntity<String> saveProduct(@RequestBody Products product)
	{
//		Products products=productRepository.save(product);
//		return products;
		try {
			productValidator.validateProduct(product);
			productService.insertProduct(product);
		    return new ResponseEntity<>("success",HttpStatus.OK);
			
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("products/allProducts")
	public ResponseEntity<?> getAllProducts() throws ClassNotFoundException, SQLException
	{
		try 
		{
			List<Products> products=productService.getAllProducts();
			return new ResponseEntity<>(products,HttpStatus.OK);
		}
		catch(Exception e)
		{
			Message message=new Message();
			message.setMessage(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("products/get/product")
	 public ResponseEntity<?> getProductUsingId(@RequestParam("id") int id)
	 {
		try {
		    Products product=productRepository.getProductById(id);
		    return new ResponseEntity<>(product,HttpStatus.OK);
		}
		catch(Exception e)
		{
			Message message=new Message();
			message.setMessage(e.getMessage());
			System.out.println(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
			
		}
	 }
	
	@GetMapping("products/get/brand")
	public List<Products> getUsingBrand(@RequestParam("brand") String brand)
	{
		 List<Products> products=productRepository.getProductsByBrand(brand);
	     return products;
	}
	
	
	@GetMapping("products/get/category")
	public List<Products> getByCategory(@RequestParam("category") String category)
	{
		List<Products> products=productRepository.getProductsByCategory(category);
		return products;
	}
	
	@GetMapping("products/get/stationary/sort/asc")
	public List<Products> getAndSortByPrice()
	{
		List<Products> products=productRepository.findAll();
		return products;
	}
	
	@GetMapping("prodcut/delete")
	public String deleteProductById(@RequestParam("id") int id)
	{
		productRepository.deleteById(id);
		return "deleted";
	}
	
	@GetMapping("product/update")
	public String updateWalletById(@RequestParam("id") int id,@RequestParam("points") int points)
	{
		productRepository.updateProductById(points, id);
		return "updated";
	}
	 
	
	

}
