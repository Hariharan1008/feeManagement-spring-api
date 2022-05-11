package com.feemanagementapp.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feemanagementapp.dao.FeesStatusDao;
import com.feemanagementapp.dao.FeesStructureDao;
import com.feemanagementapp.dao.UpdateFeesStructureDao;
import com.feemanagementapp.model.FeesStructure;
import com.feemanagementapp.model.Message;
import com.feemanagementapp.service.FeeService;
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
	public ResponseEntity<?> getFeesStructure(@RequestParam("year") int year,@RequestParam("branch") String branch) throws Exception
	{
		try
		{
			FeeService feeService= new FeeService();
			FeesStructure fee=feeService.getFeesStructure(year, branch);
			return new ResponseEntity<>(fee,HttpStatus.OK);
		}
		catch(Exception e)
		{
			Message message=new Message();
			message.setMessage(e.getMessage());
		   return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
			
		}
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
