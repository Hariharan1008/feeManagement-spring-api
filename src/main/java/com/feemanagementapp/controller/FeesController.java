package com.feemanagementapp.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feemanagementapp.dao.FeesStatusDao;
import com.feemanagementapp.dao.FeesStructureDao;
import com.feemanagementapp.dao.UpdateFeesStructureDao;
import com.feemanagementapp.model.FeesStructure;
import com.feemanagementapp.service.FeesStructureValidator;




@RestController
public class FeesController {
	
	@GetMapping("fees/feesStatus")
	public FeesStructure getFeesStatus(@RequestParam("email") String email) throws Exception
	{
		System.out.print(email);
		if(email.contains("@") && email.contains("."))
		{
		  FeesStructure fee=FeesStatusDao.myFees(email);
		  return fee;
		}
		return null;
	}
	
	
	@GetMapping("fees/feesStructure")
	public FeesStructure getFeesStructure(@RequestParam("year") int year,@RequestParam("branch") String branch) throws Exception
	{
		FeesStructure fees=FeesStructureDao.getFeesStructure(year,branch);
		return fees;
	}
	
	@GetMapping("fees/update/fees")
	public String updateFeesStructure(@RequestParam("firstSemesterFees") String firstSemesterFees,@RequestParam("secondSemesterFees") String secondSemesterFees,@RequestParam("transportFees") String transportFees,@RequestParam("hostelFees") String hostelFees,@RequestParam("year") int year,@RequestParam("branch") String branch)
	{
		String message=null;
		FeesStructure fee=new FeesStructure();
		fee.setFirstSemesterFees(firstSemesterFees);
		fee.setSecondSemesterFees(secondSemesterFees);
		fee.setTransportFees(transportFees);
		fee.setHotelFees(hostelFees);
		try {
			int verified=FeesStructureValidator.validateFeesAmount(fee);
			if(verified==1)
			{
				UpdateFeesStructureDao update=new UpdateFeesStructureDao();
				update.updateFeesStructure(fee, year,branch);
				message="success";
			}
		}
		catch(Exception e)
		{
			message=e.getMessage();
		}
		return message;
	}
	 
	
	

}
