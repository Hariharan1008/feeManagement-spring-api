package com.feemanagementapp.service;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import com.feemanagementapp.dao.FindWalletBalanceDao;
import com.feemanagementapp.dao.UpdateWalletTransactionDao;
import com.feemanagementapp.dao.WalletAndTransactionUpdatorDao;
import com.feemanagementapp.model.Wallet;




public class SubtractMoneyFromWallet {
	public int walletUpdatiorAfterPayment(long mobile,long amount) throws ClassNotFoundException, SQLException
	{
		FindWalletBalanceDao findBalance=new FindWalletBalanceDao();
		long balance=findBalance.findWalletBalance(mobile);
		long updatedBalance=balance-amount;
		WalletAndTransactionUpdatorDao walletUpdate=new  WalletAndTransactionUpdatorDao();
		int completed=walletUpdate.walletUpdationAfterPayment(mobile, updatedBalance);
		if(completed==1)
		{
			UpdateWalletTransactionDao TransactionUpdate=new UpdateWalletTransactionDao();
			Wallet wallet=new Wallet();
			wallet.setAmount(amount);
			wallet.setMobile(mobile);
			wallet.setOperation("debited");
			wallet.setDate(Date.valueOf(LocalDate.now()));
			int updated=TransactionUpdate.updateWalletTransaction(wallet);
			return updated;
		}
		return 0;
	}

}
