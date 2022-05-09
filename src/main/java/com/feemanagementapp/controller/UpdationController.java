package com.feemanagementapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feemanagementapp.dao.FeesUpdationRepository;
import com.feemanagementapp.model.Fees;

@RestController
public class UpdationController {
	@Autowired
	FeesUpdationRepository update;
	
//	@GetMapping("fees/updation")
//	public int updateByYear(@RequestParam("year") int year,@RequestParam("firstSemester") String firstSemester,@RequestParam("branch") String branch)
//	{
//		int rows=update.updateByYear(firstSemester,year,branch);
//		return rows;
//	}

}
