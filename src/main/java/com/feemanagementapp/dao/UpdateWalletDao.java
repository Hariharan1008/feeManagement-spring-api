package com.feemanagementapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateWalletDao{
	public int updateMyWallet(long mobile,long updatedBalance) throws ClassNotFoundException, SQLException
	{
		Connection connection=ConnectionUtil.databaseConnection();
		PreparedStatement statement=null;
		String query="update fees_wallet set balance=? where mobile_no=? ";
		statement=connection.prepareStatement(query);
		statement.setLong(1,updatedBalance);
		statement.setLong(2, mobile);
		int rows=statement.executeUpdate();
		return rows;	
	}
	public int updateWalletPoints(long mobile,int amount) throws ClassNotFoundException, SQLException
	{
		int points=amount/10;
		FindWalletPoints existing=new FindWalletPoints();
		int existingPoints=existing.getWalletPoints(mobile);
		points=points+existingPoints;
		Connection connection=ConnectionUtil.databaseConnection();
		PreparedStatement statement=null;
		String query="update fees_wallet set wallet_points=? where mobile_no=? ";
		statement=connection.prepareStatement(query);
		statement.setLong(1,points);
		statement.setLong(2, mobile);
		int rows=statement.executeUpdate();
		connection.close();
		return rows;
	}

}
