package com.feemanagementapp.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feemanagementapp.dao.LoginValidationUsingDatabase;
import com.feemanagementapp.dao.RegisterInsertion;
import com.feemanagementapp.dao.RegistrationInsertionAdminDao;
import com.feemanagementapp.dao.UserRepository;
import com.feemanagementapp.model.FeesStructure;
import com.feemanagementapp.model.Registration;
import com.feemanagementapp.model.Session;
import com.feemanagementapp.service.RegisterValidator;
import com.feemanagementapp.service.TotalFeesFinder;



@RestController
public class UserController {
	@Autowired
	UserRepository user;
	@GetMapping("register/verification")
	public String verifyUser(@RequestParam("name") String name,@RequestParam("userName") String userName,@RequestParam("mobile") String mobile,@RequestParam("email") String email,@RequestParam("password") String password,@RequestParam("age") String age) throws Exception
	{
        Registration registration=new Registration();
		registration.setName(name);
		registration.setUserName(userName);
		registration.setUserMobileNumber(mobile);
		int userAge=Integer.parseInt(age);
		registration.setAge(userAge);
		registration.setUserEmail(email);
		registration.setUserPassword(password);
		String message;
		int result=RegisterValidator.validatingRegistration(registration);
		if(result==1)
		{
			message="successfull";
		}
		else
		{
			message="failure";
		}
		return message;
	}
	
	@GetMapping("register/insertion")
	public String insertUser(@RequestParam("name") String name,@RequestParam("userName") String userName,@RequestParam("mobile") String mobile,@RequestParam("email") String email,@RequestParam("password") String password,@RequestParam("age") String age,@RequestParam("year") int year,@RequestParam("branch") String branch,@RequestParam("hostelCheck") String hOrD,@RequestParam("bus") String needBus) throws Exception
	{
        Registration registration=new Registration();
		registration.setName(name);
		registration.setUserName(userName);
		registration.setUserMobileNumber(mobile);
		int userAge=Integer.parseInt(age);
		registration.setAge(userAge);
		registration.setUserEmail(email);
		registration.setUserPassword(password);
		registration.setYearOfStudy(year);
		registration.setBranch(branch);
		registration.sethOrD(hOrD);
		registration.setNeedBus(needBus);
//		int userInsert=0;
//		userInsert = RegisterInsertion.insertUser(registration);
		user.save(registration);
		String totalFees=null;
		totalFees = TotalFeesFinder.totalFeeFinder(year,branch,hOrD,needBus);
		System.out.println(year+" "+branch+" "+hOrD+" "+needBus);
		FeesStructure fee= new FeesStructure();
		fee.setEmail(email);
		fee.setName(name);
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
		if( adminInsert==1)
		{
			Session.setSessionmobile(mobile);
			Session.setSessionEmail(email);
			message="Successfully Inserted";
		}
		else
		{
			message="found an error";
		}
		return message;
	}
	
	@GetMapping("user/login")
	public String loginValidation(@RequestParam("email") String email,@RequestParam("password") String password) throws Exception
	{
        String valid=null;
		valid=LoginValidationUsingDatabase.loginValidator(email,password);
		String message=null;
		if(valid.length()==10)
		{
			Session.setSessionEmail(email);
			Session.setSessionmobile(valid);
			message="Welcome";
		}
		else if(valid=="fail")
		{
			message= "no records found";
		}
		else
		{
			message ="invalid credentials";
		}
		return message;
	}
	@GetMapping("user/sessionMobile")
	public long getSessionMobile(@RequestParam("email") String email)
	{
		long mobile=user.getMobileByEmail(email);
		return mobile;
	}
	
	@GetMapping("user/check/email")
	public String findEmail(@RequestParam("email") String userEmail)
	{
		String email=user.getEmail(userEmail);
		if(email!=null)
		{
			return "success";
		}
		else
		{
			return "no user found";
		}
	}
	
	@GetMapping("user/update/password")
	public String updatePassword(@RequestParam("email") String email,@RequestParam("password")String password)
	{
		user.updatePassword(password, email);
		return "success";
	}
			

}
