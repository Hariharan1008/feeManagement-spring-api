package com.feemanagementapp.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feemanagementapp.dao.LoginValidationUsingDatabase;
import com.feemanagementapp.dao.RegisterInsertion;
import com.feemanagementapp.dao.RegistrationInsertionAdminDao;
import com.feemanagementapp.dao.UserRepository;
import com.feemanagementapp.model.FeesStructure;
import com.feemanagementapp.model.Message;
import com.feemanagementapp.model.Registration;
import com.feemanagementapp.model.Session;
import com.feemanagementapp.service.RegisterValidator;
import com.feemanagementapp.service.RegistrationService;
import com.feemanagementapp.service.TotalFeesFinder;



@RestController
public class UserController {
	@Autowired
	UserRepository user;
	
	@Autowired
	RegistrationService registerService; 
	
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
		String message=null;
		try
		{			 
			int result=RegisterValidator.validatingRegistration(registration);		
			  message="successfull";				
		}		
		 catch(Exception e)
	    {
	    	message=e.getMessage();
	    }
		return message;
	}
	
	@PostMapping("register/insertion")
	public ResponseEntity<?> insertUser(@RequestBody Registration registration) throws Exception
	{
		try
		{
		  
		  int result=registerService.registerInsertion(registration);
		  return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e)
		{
			Message message=new Message();
			message.setMessage(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@PostMapping("user/login")
	public ResponseEntity<?> loginValidation(@RequestBody Registration registration) throws Exception
	{
        try
        {
		  LoginValidationUsingDatabase.loginValidator(registration.getUserEmail(),registration.getUserPassword());
		  return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e)
        {
        	Message message=new Message();
        	message.setMessage(e.getMessage());
        	return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }
		
		
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
	
	@PostMapping("user/insert/admin")
	public ResponseEntity<?> insertAdmin(@RequestBody Registration registration)
	{
		try
		{
		  user.save(registration);
		  return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e)
		{
			Message message=new Message();
			message.setMessage(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
			
		}
		
	}
			

}
