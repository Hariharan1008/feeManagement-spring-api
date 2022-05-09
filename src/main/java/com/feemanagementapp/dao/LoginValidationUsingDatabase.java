package com.feemanagementapp.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginValidationUsingDatabase {
	public static String loginValidator(String email,String password) throws Exception
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
			return "fail";
		}
		else if(Password.equals(password))
		{
			
			return mobile;
		}
		else
		{
			return "Invalid credentials";
		}
	}


}
