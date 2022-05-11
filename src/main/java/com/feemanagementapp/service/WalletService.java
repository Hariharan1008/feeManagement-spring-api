package com.feemanagementapp.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feemanagementapp.dao.AddMoneyToWallet;
import com.feemanagementapp.dao.WalletInsertionDao;
import com.feemanagementapp.dao.WalletLoginValidationDao;
import com.feemanagementapp.dao.WalletTransactionRepository;
import com.feemanagementapp.model.Wallet;
import com.feemanagementapp.model.WalletTransaction;

@Component
public class WalletService {
	@Autowired
	NotificationService notificationService;
	
	@Autowired
	WalletTransactionRepository walletTransactionRepository;

	public String walletRegister(Wallet wallet) throws ClassNotFoundException, SQLException
	{
		WalletInsertionDao insert=new WalletInsertionDao();
		int completed=0;
		String result="";
		wallet.setBalance(0);
		wallet.setWalletPoints(10);
		completed=insert.walletInsertion(wallet);
		if(completed==0)
		{
			result="Creation failed";
		}
		else
		{
			result="Success";
		}
		return result;
	}
	
	public int walletVerification(long mobile,int tpin) throws Exception
	{
		   WalletLoginValidationDao login=new WalletLoginValidationDao();
			int valid=login.validateWalletlogin(mobile,tpin);
			if(valid==1)
			{
				return 1;
			}
			else
			{
				throw new Exception("invalid pin");
			}
			
	}
	
	public int updateMoneyToWallet(long mobile,long amount) throws Exception
	{
		AddMoneyToWallet add=new AddMoneyToWallet();
		int completed=0;
		completed = add.addMoney(mobile, amount);
		String message=amount+"rs "+"added successfully to your wallet";
		if(completed==1)
		{
			notificationService.notificationInserter(message, mobile);
		    return 1;
		}
		else
		{
			throw new Exception("cant add money");
		}
	}
	
	public int payFeesAndUpdateUsingWallet(String email,long mobile,long amount) throws Exception
	{
		PayFeesUsingWallet pay=new PayFeesUsingWallet();
		int amountValid=0;
		amountValid = pay.amountVerifier(amount,mobile,email);
		String message=null;
		if(amountValid==0)
		{	
			UpdateFeesAfterUsingWallet updatefees=new UpdateFeesAfterUsingWallet();
			updatefees.adminTableUpdation(amount,email);
			SubtractMoneyFromWallet subtract=new SubtractMoneyFromWallet();		
			subtract.walletUpdatiorAfterPayment( mobile, amount);
			return 1;
		}
		else
		{
			 throw new Exception("Insufficient Funds");
			
		}
		
	}
	
	public List<WalletTransaction> getAllWalletTransactions(long mobile) throws Exception
	{
		List<WalletTransaction> transactions=walletTransactionRepository.getAllWalletTransactions(mobile);
		if(transactions!=null)
		{
			return transactions;
		}
		else
		{
			throw new Exception("no records found");
		}
	}

}
