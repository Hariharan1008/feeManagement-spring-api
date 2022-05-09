package com.feemanagementapp.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feemanagementapp.dao.FindWalletBalanceDao;
import com.feemanagementapp.dao.FindWalletUserUsingMobile;
import com.feemanagementapp.dao.UpdateWalletDao;
import com.feemanagementapp.model.FundTransfer;
import com.feemanagementapp.service.NotificationService;
import com.feemanagementapp.service.UpdateFundTransaction;





@RestController
public class FundTransferController {
	@Autowired
	NotificationService notificationservice;
	
	@GetMapping("fundTransfer/findUser")
	public String findWalletUser(@RequestParam("receiverMobile") long receiverMobile) throws ClassNotFoundException, SQLException
	{
		FindWalletUserUsingMobile findName=new FindWalletUserUsingMobile();
	     String userName=null;
	     userName = findName.findUser(receiverMobile);
	     String user="";
	      if(userName!=null)
	      {
		     user=userName;
	      }
	      else
	      {
	    	  user="no user found";
	      }
	      return user;
	}
	@GetMapping("fundTransfer/updateAllTransactions")
	public String UpdateAllTransactionDetails(@RequestParam("senderMobile") long senderMobile,@RequestParam("receiverMobile") long receiverMobile,@RequestParam("amount") long amount) throws ClassNotFoundException, SQLException
	{
		FindWalletBalanceDao findBalance=new FindWalletBalanceDao();
	    String message="";
	    long senderBalance=0;
	    long receiverBalance=0;
	    senderBalance=findBalance.findWalletBalance(senderMobile);
	    receiverBalance=findBalance.findWalletBalance(receiverMobile);
	    if(amount<=senderBalance)
		{
			FundTransfer transfer=new FundTransfer();
			transfer.setAmount(amount);
			transfer.setSenderMobile(senderMobile);
			transfer.setReceiverMobile(receiverMobile);
			transfer.setSenderBalance(senderBalance);
			transfer.setReceiverBalance(receiverBalance);
			UpdateFundTransaction fund=new UpdateFundTransaction();
			fund.updateWallet(transfer);
			UpdateWalletDao points=new UpdateWalletDao();
			points.updateWalletPoints(senderMobile,(int)amount);
			String senderStatus=amount+"rs "+"sent successfully to"+" "+receiverMobile;
			String receiverStatus=amount+"rs "+"received successfully "+senderMobile; 
			notificationservice.notificationInserter(senderStatus,senderMobile);
			notificationservice.notificationInserter(receiverStatus,receiverMobile);
			message="completed";
		}
		else
		{
			message="Insufficient funds";
		}
	    return message;
	}
}
