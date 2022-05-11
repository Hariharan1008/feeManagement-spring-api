package com.feemanagementapp.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginValidationUsingDatabase {
	public static int loginValidator(String email,String password) throws Exception
	{
		Connection connection ;
	    PreparedStatement statement;
	    ResultSet rs = null;
	    connection = ConnectionUtil.databaseConnection();
		String query = "SELECT User_Mobile,user_Email,User_Password FROM fees_user WHERE user_Email=?";
		statement = connection.prepareStatement(query);
        statement.setString(1, email);  
        rs = statement.executeQuery();
        String mobile=null;
        String mail=null;
        String Password=null;
		while(rs.next())
		{
		 mobile=rs.getString("User_Mobile");	
		 mail=rs.getString("user_Email");
		 Password=rs.getString("User_Password");
		}
		if(mail==null)
		{
			throw new Exception("no records found");
		}
		else if(Password.equals(password))
		{
			
			return 1;
		}
		else
		{
			throw new Exception("Invalid credentials");
		}
	}


}
