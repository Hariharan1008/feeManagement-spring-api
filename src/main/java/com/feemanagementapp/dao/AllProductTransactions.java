package com.feemanagementapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.feemanagementapp.model.MyProductTransaction;

public class AllProductTransactions {
  public List<MyProductTransaction> getAllProductTransactions(long mobile) throws ClassNotFoundException, SQLException
  {
	  List<MyProductTransaction> products=new ArrayList<MyProductTransaction>();
	  Connection connection=ConnectionUtil.databaseConnection();
	  PreparedStatement statement=null;
	  ResultSet result=null;
	  String query="SELECT * FROM my_all_products WHERE mobile=?";
	  statement=connection.prepareStatement(query);
	  statement.setLong(1, mobile);
	  result=statement.executeQuery();
	  int id=0;
	  String productName=null;
	  int walletPoints=0;
	  String image=null;
	  MyProductTransaction product=null;
	  Date purchasedOn=null;
	  while(result.next())
	  {
		  product=new MyProductTransaction();
		  id=result.getInt("id");
		  productName=result.getString("name");
		  walletPoints=result.getInt("wallet_points");
		  image=result.getString("image");
		  purchasedOn=result.getDate("purchased_on");
		  product.setProductName(productName);
		  product.setWalletPoints(walletPoints);
		  product.setPurchasedOn(purchasedOn);
		  product.setImage(image);
		  products.add(product);
	  }
	  connection.close();
	  return products;
  }
}
