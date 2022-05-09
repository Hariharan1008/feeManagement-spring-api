package com.feemanagementapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindWalletBalanceDao {
	public long findWalletBalance(long mobile) throws ClassNotFoundException, SQLException
	{
		Connection connection=ConnectionUtil.databaseConnection();
		PreparedStatement statement=null;
		ResultSet result=null;
		String query="select balance from fees_wallet where mobile_no=?";
		statement=connection.prepareStatement(query);
		statement.setLong(1, mobile);
		result=statement.executeQuery();
		long balance=0;
		if(result.next())
		{
			balance=result.getLong("balance");
			
			return balance;
		}
		else
		{
			
			return -1;
		}
		
		
	}

}
