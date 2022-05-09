package com.feemanagementapp.service;

import com.feemanagementapp.dao.FeesUpdationDao;
import com.feemanagementapp.dao.TotalAndPaidFeesFinder;
import com.feemanagementapp.model.FeesStructure;

public class UpdateFeesAfterUsingWallet {
	public void adminTableUpdation(long amount,String email) throws Exception
	{
		FeesStructure feesStructure=TotalAndPaidFeesFinder.totalFeesFinder(email);
		long paidFees=Long.parseLong(feesStructure.getFeesPaid());
		long pendingFees=Long.parseLong(feesStructure.getFeesPending());
		paidFees=paidFees+amount;
		pendingFees=pendingFees-amount;
		feesStructure.setFeesPaid(Long.toString(paidFees));
		feesStructure.setFeesPending(Long.toString(pendingFees));
		feesStructure.setPayingAmount(Long.toString(amount));
		FeesUpdationDao.feesUpdation(feesStructure);
	}

}
