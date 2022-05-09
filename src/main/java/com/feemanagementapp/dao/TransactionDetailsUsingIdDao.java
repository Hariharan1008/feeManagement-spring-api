package com.feemanagementapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.feemanagementapp.model.Transaction;






public class TransactionDetailsUsingIdDao {
	public static Transaction usingTransactionId(int transactionId,String email) throws SQLException, ClassNotFoundException
	{
		Connection connection = ConnectionUtil.databaseConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		String query = "SELECT * FROM Transaction_Details WHERE Transaction_Id=? and Email=?";
		statement = connection.prepareStatement(query);
		statement.setInt(1, transactionId);
		statement.setString(2, email);
		result = statement.executeQuery();
		int id=0;
		String paid=null;
		Date date=null;
		Transaction transaction=null;
		if(result.next())
       {
			transaction=new Transaction();
			id=result.getInt("Transaction_Id");
    	    paid=result.getString("Paid_Amount");
    	    date=result.getDate("Paid_On");
    	    transaction.setPaidAmount(paid);
    	    transaction.setTransactionId(transactionId);
    	    transaction.setPaidOn(date);
    	    
       }
		connection.close();
		return transaction;
	}

}
