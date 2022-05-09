package com.feemanagementapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.feemanagementapp.model.FeesStructure;




public class TotalAndPaidFeesFinderDao {
	static Scanner sc=new Scanner(System.in);
	public static FeesStructure totalFeesFinder(String email) throws Exception
	{
		Connection connection=null;
		PreparedStatement statement=null;
	    ResultSet rs = null;
	    connection=ConnectionUtil.databaseConnection();
		String query = "SELECT Total_Fees,Fees_paid,Fees_Pending FROM Fees_Admin WHERE Email=?";
		statement = connection.prepareStatement(query);
        statement.setString(1, email);  
        rs = statement.executeQuery();
        String totalFees=null;
        String feesPaid=null;
        String feesPending=null;
        FeesStructure feesStructure=null;
		if(rs.next())
		{ 
			feesStructure=new FeesStructure();
			totalFees=rs.getString("Total_Fees");
		    feesPaid=rs.getString("Fees_paid");
			feesPending=rs.getString("Fees_Pending");
			feesStructure.setTotalFees(totalFees);
			feesStructure.setFeesPaid(feesPaid);
			feesStructure.setFeesPending(feesPending);
		}
		  connection.close();
		  return feesStructure;

	}
	
	

}
