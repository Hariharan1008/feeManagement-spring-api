package com.feemanagementapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.feemanagementapp.model.Wallet;







public class UpdateWalletTransactionDao {
	public int updateWalletTransaction(Wallet wallet) throws ClassNotFoundException, SQLException
	{
		Connection connection=ConnectionUtil.databaseConnection();
		PreparedStatement statement=null;
		String query="insert into wallet_transactions(mobile_no,amount,operation,performed_on)values(?,?,?,?)";
		statement =connection.prepareStatement(query);
		statement.setLong(1, wallet.getMobile());
		statement.setLong(2, wallet.getAmount());
		statement.setString(3, wallet.getOperation());
		statement.setDate(4, wallet.getDate());
		int rows=statement.executeUpdate();
		connection.close();
		return rows;
	}

}
