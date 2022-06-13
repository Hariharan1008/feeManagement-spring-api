package com.feemanagementapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.feemanagementapp.model.Products;

public class GetAllProdutsDao {
	public static List<Products> findAllProducts() throws ClassNotFoundException, SQLException
	{
		List<Products> list=new ArrayList<Products>();
		Connection connection=ConnectionUtil.databaseConnection();
		PreparedStatement statement;
		ResultSet result=null;
		String query="SELECT * FROM wallet_products";
		statement=connection.prepareStatement(query);
		result=statement.executeQuery();
		Products product=null;
		int id=0;
		String name=null;
		int walletPoints=0;
		String imageUrl=null;
		long price=0;
		while(result.next())
		{
			product=new Products();
			id=result.getInt("id");
			name=result.getString("name");
			imageUrl=result.getString("image");
			walletPoints=result.getInt("wallet_points");
			price=result.getLong("price");
			product.setName(name);
			product.setImageUrl(imageUrl);
			product.setPrice(price);
			product.setId(id);
			product.setWalletPoints(walletPoints);
			list.add(product);
		}
		connection.close();
		return list;
	}

}
