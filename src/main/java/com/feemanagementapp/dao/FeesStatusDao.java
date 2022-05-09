package com.feemanagementapp.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.feemanagementapp.model.FeesStructure;


public class FeesStatusDao {
	public static FeesStructure myFees(String email) throws Exception
	{
		Connection connection;
	    PreparedStatement statement;
	    ResultSet rs = null;
	    connection = ConnectionUtil.databaseConnection();
		String query = "SELECT * FROM Fees_Admin WHERE Email=?";
		statement = connection.prepareStatement(query);
        statement.setString(1, email); 
        rs = statement.executeQuery();
		String totalFees=null;
		String feesPaid=null;
		String feesPending=null;
		FeesStructure fee=new FeesStructure();
		if(rs.next())
		{
			
			totalFees=rs.getString("Total_Fees");
			feesPaid=rs.getString("Fees_paid");
			feesPending=rs.getString("Fees_Pending");
			
			 
		}
		fee.setTotalFees(totalFees);
		fee.setFeesPaid(feesPaid);
		fee.setFeesPending(feesPending);
		connection.close();
		return fee;

	}

}
