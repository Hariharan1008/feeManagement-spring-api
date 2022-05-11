package com.feemanagementapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feemanagementapp.dao.FindWalletBalanceDao;
import com.feemanagementapp.dao.FindWalletUserUsingMobile;
import com.feemanagementapp.dao.UpdateWalletDao;
import com.feemanagementapp.model.FundTransfer;

@Component
public class FundTransferService {
	@Autowired
	NotificationService notificationService;
	public String getUserName(long mobile) throws Exception
	{
		FindWalletUserUsingMobile findName=new FindWalletUserUsingMobile();
	     String userName=null;
	     userName = findName.findUser(mobile);
	     String user="";
	      if(userName!=null)
	      {
		     return userName;
	      }
	      else
	      {
	    	  throw new Exception("no user found");
	      }
	      
	}
	public int updateAllFundTransactions(long senderMobile,long receiverMobile,long amount) throws Exception
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
			notificationService.notificationInserter(senderStatus,senderMobile);
			notificationService.notificationInserter(receiverStatus,receiverMobile);
			return 1;
		}
		else
		{
			throw new Exception("insufficient funds");
		}
	}
 
}
