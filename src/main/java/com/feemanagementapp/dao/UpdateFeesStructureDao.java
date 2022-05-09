package com.feemanagementapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.feemanagementapp.model.FeesStructure;

public class UpdateFeesStructureDao {
	public int updateFeesStructure(FeesStructure fee,int year,String dept ) throws SQLException, ClassNotFoundException
	{
		Connection connection=ConnectionUtil.databaseConnection();
		PreparedStatement statement=null;
		String query="update fees_structure set First_Semester_Fees=?,Second_Semester_Fees=?,Hostel_Fees=?,Transport_Fees=? where Year=? and Dept=?";
		statement= connection.prepareStatement(query);
		statement.setString(1, fee.getFirstSemesterFees());
		statement.setString(2, fee.getSecondSemesterFees());
		statement.setString(3, fee.getHotelFees());
		statement.setString(4,fee.getTransportFees());
		statement.setInt(5, year);
		statement.setString(6,dept);
		int rows=statement.executeUpdate();
		return rows;
	}

}
