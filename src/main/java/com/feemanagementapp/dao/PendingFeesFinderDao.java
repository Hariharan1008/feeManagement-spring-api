package com.feemanagementapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PendingFeesFinderDao {
	public long findPendingFees(String email) throws ClassNotFoundException, SQLException
	{
		Connection connection=ConnectionUtil.databaseConnection();
		PreparedStatement statement=null;
		ResultSet result=null;
		String query="select Fees_Pending from Fees_Admin where Email=?";
		statement=connection.prepareStatement(query);
		statement.setString(1, email);
		result=statement.executeQuery();
		String fees=null;
		if(result.next())
		{
			fees=result.getString("Fees_Pending");
		}
		long totalFees=Long.parseLong(fees);
		connection.close();
		return totalFees;
		
		
	}

}
