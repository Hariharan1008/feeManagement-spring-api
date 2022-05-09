package com.feemanagementapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.feemanagementapp.model.FeesStructure;




public class RegistrationInsertionAdminDao {
	public static int userInsertionInAdminTable(FeesStructure fee) throws ClassNotFoundException, SQLException
	{
		Connection connection ;
	    PreparedStatement statement;
	    connection = ConnectionUtil.databaseConnection();
		String query="insert into Fees_Admin(Name,Email,Total_Fees,Fees_Paid,Fees_Pending,Paid_Amout,Last_Fees_paid)values(?,?,?,?,?,?,?)";
		statement = connection.prepareStatement(query);
		statement.setString(1, fee.getName());
		statement.setString(2, fee.getEmail());
		statement.setString(3,fee.getTotalFees());
		statement.setString(4,fee.getFeesPaid());
		statement.setString(5,fee.getFeesPending());
		statement.setString(6,fee.getPayingAmount());
		statement.setDate(7,fee.getPaidOn());
        int rows = statement.executeUpdate();
       // System.out.println("No of rows inserted:" + rows);
        connection.close();
        return rows;
	
	}

}
