package com.feemanagementapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindWalletPoints {
	public int getWalletPoints(long mobile) throws ClassNotFoundException, SQLException
	{
		Connection connection=ConnectionUtil.databaseConnection();
		PreparedStatement statement=null;
		ResultSet result=null;
		String query="select wallet_points from fees_wallet where mobile_no=?";
		statement=connection.prepareStatement(query);
		statement.setLong(1, mobile);
		result=statement.executeQuery();
		int walletPoints=0;
		if(result.next())
		{
			walletPoints=result.getInt("wallet_points");	
		}
		connection.close();
		return walletPoints;
	}
    public int getProductPoints(int id) throws ClassNotFoundException, SQLException
    {
    	Connection connection=ConnectionUtil.databaseConnection();
		PreparedStatement statement=null;
		ResultSet result=null;
		String query="select wallet_points from wallet_products where id=?";
		statement=connection.prepareStatement(query);
		statement.setLong(1, id);
		result=statement.executeQuery();
		int walletPoints=0;
		if(result.next())
		{
			walletPoints=result.getInt("wallet_points");	
		}
		connection.close();
		return walletPoints;
    }
}
