package com.feemanagementapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WalletLoginValidationDao {
	public int validateWalletlogin(long mobile,int tpin) throws ClassNotFoundException, SQLException
	{
		Connection connection=ConnectionUtil.databaseConnection();
		PreparedStatement statement=null;
		ResultSet result=null;
		String query="select mobile_no,tpin from fees_wallet where mobile_no=? and tpin=?";
		statement=connection.prepareStatement(query);
		statement.setLong(1, mobile);
		statement.setInt(2, tpin);
		result=statement.executeQuery();
		if(result.next())
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}

}
