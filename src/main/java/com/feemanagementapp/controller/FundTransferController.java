package com.feemanagementapp.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feemanagementapp.dao.FindWalletBalanceDao;
import com.feemanagementapp.dao.FindWalletUserUsingMobile;
import com.feemanagementapp.dao.UpdateWalletDao;
import com.feemanagementapp.model.FundTransfer;
import com.feemanagementapp.model.Message;
import com.feemanagementapp.service.FundTransferService;
import com.feemanagementapp.service.NotificationService;
import com.feemanagementapp.service.UpdateFundTransaction;





@RestController
public class FundTransferController {
	@Autowired
	NotificationService notificationservice;
	
	@Autowired
	FundTransferService fundTransferService;
	
	@GetMapping("fundTransfer/findUser")
	public ResponseEntity<?> findWalletUser(@RequestParam("receiverMobile") long receiverMobile) throws ClassNotFoundException, SQLException
	{
		try {
			FundTransferService fundTransferService=new FundTransferService();
			String userName=fundTransferService.getUserName(receiverMobile);
			if(userName!=null)
			{
				Message message=new Message();
				message.setMessage(userName);
				return new ResponseEntity<>(message,HttpStatus.OK);
			}
			else
			{
				Message message=new Message();
				message.setMessage("cant process your request at this time");
				return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
			}		
		}
		catch(Exception e)
		{
			Message message=new Message();
			message.setMessage(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("fundTransfer/updateAllTransactions")
	public ResponseEntity<?> UpdateAllTransactionDetails(@RequestParam("senderMobile") long senderMobile,@RequestParam("receiverMobile") long receiverMobile,@RequestParam("amount") long amount) throws ClassNotFoundException, SQLException
	{
		try 
		{
			int completed=fundTransferService.updateAllFundTransactions(senderMobile, receiverMobile, amount);
			if(completed==1)
			{
				return new ResponseEntity<>(HttpStatus.OK);
			}
			else
			{
				Message message=new Message();
				message.setMessage("cant process your request at this time");
				return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
			}		
		}
		catch(Exception e)
		{
			Message message=new Message();
			message.setMessage(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
	
	        
	}
}
