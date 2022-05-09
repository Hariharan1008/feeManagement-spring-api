package com.feemanagementapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import com.feemanagementapp.model.FundTransfer;






public class UpdateFundTransferTransactionsDao {
	public static int transactionId=1234567;
	
	public int updateFundTransaction(FundTransfer fund) throws ClassNotFoundException, SQLException
	{
		Connection connection=ConnectionUtil.databaseConnection();
		PreparedStatement statement=null;
		String query="insert into fund_transactions(transaction_id,sender,receiver,amount,transfered_on)values(?,?,?,?,?)";
		statement=connection.prepareStatement(query);
		statement.setInt(1,getTransactionId());
		statement.setLong(2, fund.getSenderMobile());
		statement.setLong(3, fund.getReceiverMobile());
		statement.setLong(4, fund.getAmount());
		statement.setDate(5, Date.valueOf(LocalDate.now()));
		int rows=statement.executeUpdate();
		connection.close();
		return rows;
	}
	public static int getTransactionId()
	{
		transactionId++;
		int b = (int)(Math.random()*(500000-800000+1)+500000);
		return transactionId+b;
	}

}
