package com.feemanagementapp.service;
import java.sql.SQLException;

import com.feemanagementapp.dao.FindWalletBalanceDao;
import com.feemanagementapp.dao.PendingFeesFinderDao;
import com.feemanagementapp.model.Session;
import com.feemanagementapp.model.Wallet;
public class PayFeesUsingWallet {
	public Wallet findFeesAndWalletBalance(String email,long mobile) throws ClassNotFoundException, SQLException
	{
		PendingFeesFinderDao total=new PendingFeesFinderDao();
		long pendingFees=total.findPendingFees(email);
		FindWalletBalanceDao walletBal=new FindWalletBalanceDao();
		long walletBalance=walletBal.findWalletBalance(mobile);
	//	System.out.println("your pending fee is        : "+pendingFees);
//		System.out.println("your walletBalance is      : "+walletBalance);
		Wallet wallet=new Wallet();
		wallet.setPendingFees(pendingFees);
		wallet.setBalance(walletBalance);
		return wallet;
	}
	public int amountVerifier(long amount,long mobile,String email) throws ClassNotFoundException, SQLException
	{
		//String email=Session.getSessionEmail();
		//long mobile=Long.parseLong(Session.getSessionmobile());
		PendingFeesFinderDao total=new PendingFeesFinderDao();
		long pendingFees=total.findPendingFees(email);
		FindWalletBalanceDao walletBal=new FindWalletBalanceDao();
		long walletBalance=walletBal.findWalletBalance(mobile);
		if(amount>walletBalance)
		{
			return 1;
		}
		else if(amount>pendingFees)
		{
			return 2;
		}
		else
		{
			return 0;
		}
	}

}
