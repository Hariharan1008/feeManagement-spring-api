package com.feemanagementapp.service;

import com.feemanagementapp.model.FeesStructure;

public class FeesStructureValidator {
	public static  int validateFeesAmount(FeesStructure fee) throws Exception
	{
		int firstSemesterFeesValid=validateFees(fee.getFirstSemesterFees());
		int secondSemesterFeesValid=validateFees(fee.getSecondSemesterFees());
		
		int transportFeesValid=validateFees(fee.getTransportFees());
		int hostelFeesValid=validateFees(fee.getHotelFees());
		if(firstSemesterFeesValid+secondSemesterFeesValid+transportFeesValid+ hostelFeesValid==4)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	
	}
	public static int validateFees(String fees) throws Exception
	{
		int fee=Integer.parseInt(fees);
		if(fee<=0)
		{
			throw new Exception("fees cannot be null");
		}
		else
		{
			return 1;
		}
	}

}
