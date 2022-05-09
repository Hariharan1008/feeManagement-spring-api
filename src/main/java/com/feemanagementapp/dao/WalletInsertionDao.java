package com.feemanagementapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.feemanagementapp.model.Wallet;





public class WalletInsertionDao {
	public int walletInsertion(Wallet wallet) throws ClassNotFoundException, SQLException
	{
		Connection connection=ConnectionUtil.databaseConnection();
		PreparedStatement statement=null;
		String query="insert into fees_wallet(mobile_no,balance,tpin,name,wallet_points)values(?,?,?,?,?)";
		statement=connection.prepareStatement(query);
		statement.setLong(1, wallet.getMobile());
		statement.setLong(2, wallet.getBalance());
		statement.setInt(3, wallet.getTpin());
		statement.setString(4,wallet.getName());
		statement.setInt(5, wallet.getWalletPoints());
		int rows=statement.executeUpdate();
		connection.close();
		return rows;
	}

}
