package com.feemanagementapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindWalletUserUsingMobile {
	public String findUser(long mobile) throws ClassNotFoundException, SQLException
	{
		Connection connection=ConnectionUtil.databaseConnection();
		PreparedStatement statement=null;
		ResultSet res=null;
		String query="select name from fees_wallet where mobile_no=?";
		statement=connection.prepareStatement(query);
		statement.setLong(1,mobile);
		res=statement.executeQuery();
		String name=null;
		if(res.next())
		{
			name=res.getString("name");
			return name;
		}
		connection.close();
		return null;
	}

}
