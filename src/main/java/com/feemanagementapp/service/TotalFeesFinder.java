package com.feemanagementapp.service;

import java.sql.SQLException;

import com.feemanagementapp.dao.TotalFeesFinderUsingYearAndBranch;
import com.feemanagementapp.model.FeesStructure;


public class TotalFeesFinder {
	public static String totalFeeFinder(int year,String branch,String hOrD,String needBus) throws ClassNotFoundException, SQLException
	{
		FeesStructure fee=TotalFeesFinderUsingYearAndBranch.findTotalFees(year, branch);
		int firstSemesterFees=Integer.parseInt(fee.getFirstSemesterFees());
		int secondSemesterFees=Integer.parseInt(fee.getSecondSemesterFees());
		int transportFees=Integer.parseInt(fee.getTransportFees());
		int hostelFees=Integer.parseInt(fee.getHotelFees());
		int totalFees=0;
		if(hOrD.equals("H"))
		{
			totalFees=firstSemesterFees+secondSemesterFees+hostelFees;
		}
		else if(hOrD.equals("D") && needBus.equals("Yes"))
		{
			totalFees=firstSemesterFees+secondSemesterFees+transportFees;
		}
		else if(hOrD.equals("D") && needBus.equals("No"))
		{
			totalFees=firstSemesterFees+secondSemesterFees;
		}
		String totalFee=Integer.toString(totalFees);
		return totalFee;




		
	}

}
