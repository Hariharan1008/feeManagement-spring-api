package com.feemanagementapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import com.feemanagementapp.model.FeesStructure;






public class FeesUpdationDao {
	public static int transactionId=123456;
	public static int getTransactionId()
	{
		transactionId++;
		int b = (int)(Math.random()*(500000-800000+1)+500000);
		return transactionId+b;
	}
	public static int feesUpdation(FeesStructure feesStructure) throws ClassNotFoundException, SQLException
	{
		//LocalDate date=LocalDate.now();
		Connection connection=ConnectionUtil.databaseConnection();
		PreparedStatement statement=null;
		String query="update Fees_Admin set Fees_paid=?,Fees_Pending=?,Paid_Amout=?,Last_Fees_Paid=? where Email=?";
		statement = connection.prepareStatement(query);
        statement.setString(1, feesStructure.getFeesPaid());
        statement.setString(2, feesStructure.getFeesPending());
        statement.setString(3, feesStructure.getPayingAmount());
        statement.setDate(4, Date.valueOf(LocalDate.now()));
        statement.setString(5, feesStructure.getEmail());
        int rows = statement.executeUpdate();
        connection.close();
        System.out.println("Transaction completed for the amount of "+feesStructure.getPayingAmount());
        setTransactionDetails(feesStructure);
        
        return rows;
	}
	public static void setTransactionDetails(FeesStructure feesStructure) throws ClassNotFoundException, SQLException
	{
		int transactionId=getTransactionId();
		Connection connection=ConnectionUtil.databaseConnection();
		PreparedStatement statement=null;
		String query="insert into Transaction_Details(Transaction_Id,Email,Paid_On,Paid_Amount)values(?,?,?,?)";
		statement = connection.prepareStatement(query);
        statement.setInt(1, transactionId);
        statement.setString(2, feesStructure.getEmail());
        statement.setDate(3,Date.valueOf(LocalDate.now()));
        statement.setString(4, feesStructure.getPayingAmount());
        int rows = statement.executeUpdate();
        connection.close();
        System.out.println("Note down the transaction id below for your future use");
        System.out.println("Transaction Id :"+transactionId);
		
	}
	

}
