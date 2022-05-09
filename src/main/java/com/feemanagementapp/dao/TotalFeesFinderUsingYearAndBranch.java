package com.feemanagementapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.feemanagementapp.model.FeesStructure;




public class TotalFeesFinderUsingYearAndBranch {
	public static FeesStructure findTotalFees(int year,String branch) throws ClassNotFoundException, SQLException
	{
		Connection connection=ConnectionUtil.databaseConnection();
		PreparedStatement statement=null;
		ResultSet result=null;
		String query="select First_Semester_Fees,Second_Semester_Fees,Hostel_Fees,Transport_Fees from fees_structure where Year=? and Dept=?";
		statement=connection.prepareStatement(query);
		statement.setInt(1,year);
		statement.setString(2, branch);
		result=statement.executeQuery();
		String firstSemesterFees=null;
		String secondSemesterFees=null;
		String transportFees=null;
		String hostelFees=null;
		FeesStructure fee=new FeesStructure();
		if(result.next())
		{
			firstSemesterFees=result.getString("First_Semester_Fees");
			secondSemesterFees=result.getString("Second_Semester_Fees");
			transportFees=result.getString("Transport_Fees");
			hostelFees=result.getString("Hostel_Fees");
			fee.setFirstSemesterFees(firstSemesterFees);
			fee.setSecondSemesterFees(secondSemesterFees);
			fee.setTransportFees(transportFees);
			fee.setHotelFees(hostelFees);
			
		}
		connection.close();
		return fee;
		
	}

}
