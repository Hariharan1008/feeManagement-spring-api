package com.feemanagementapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.feemanagementapp.model.ProductTransaction;


public class ProductTransactionDao {
	public int updateProductTransaction(ProductTransaction transaction) throws ClassNotFoundException, SQLException
	{
		PreparedStatement statement=null;
		Connection connection=ConnectionUtil.databaseConnection();
		String query="insert into wallet_product_transaction(product_id,purchased_on,mobile)values(?,?,?)";
		statement=connection.prepareStatement(query);
		statement.setInt(1, transaction.getProductId());
		statement.setDate(2, transaction.getPurchasedOn());
		statement.setLong(3, transaction.getMobile());
		int rows=statement.executeUpdate();
		connection.close();
		return rows;
				
	}
 
}
