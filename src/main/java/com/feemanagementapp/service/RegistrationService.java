package com.feemanagementapp.service;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feemanagementapp.dao.RegistrationInsertionAdminDao;
import com.feemanagementapp.dao.UserRepository;
import com.feemanagementapp.model.FeesStructure;
import com.feemanagementapp.model.Registration;
import com.feemanagementapp.model.Session;

@Component
public class RegistrationService {
	@Autowired
	UserRepository user;
	public String registerVerification(Registration registration) throws Exception
	{
		String message=null;
		try {
		 int result=RegisterValidator.validatingRegistration(registration);
		 if(result==1)
			{
				message="successfull";
			}
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());	
		}
		
		return message;
	}
	public int registerInsertion(Registration registration) throws ClassNotFoundException, SQLException
	{
		user.save(registration);
		String totalFees=null;
		totalFees = TotalFeesFinder.totalFeeFinder(registration.getYearOfStudy(),registration.getBranch(),registration.gethOrD(),registration.getNeedBus());
		//System.out.println(year+" "+branch+" "+hOrD+" "+needBus);
		FeesStructure fee= new FeesStructure();
		fee.setEmail(registration.getUserEmail());
		fee.setName(registration.getName());
		fee.setTotalFees(totalFees);
		String paidFees=Integer.toString(0);
		fee.setFeesPaid(paidFees);
		fee.setFeesPending(totalFees);
		fee.setPayingAmount(paidFees);
		Date date =Date.valueOf(LocalDate.now());
		fee.setPaidOn(date);
		int adminInsert=0;
		adminInsert=RegistrationInsertionAdminDao.userInsertionInAdminTable(fee);
		String message;
		if(adminInsert==1)
		{
			Session.setSessionmobile(registration.getUserMobileNumber());
			Session.setSessionEmail(registration.getUserEmail());
			return 1;
		}
		else
		{
			return 0;
		}
		
	}

}
