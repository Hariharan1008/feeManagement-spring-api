package com.feemanagementapp.service;

import java.sql.SQLException;
import java.util.List;

import com.feemanagementapp.dao.FeesStructureDao;
import com.feemanagementapp.dao.MyTransactionDetailsDao;
import com.feemanagementapp.model.FeesStructure;
import com.feemanagementapp.model.Transaction;

public class FeeService {
	public FeesStructure getFeesStructure(int year,String branch) throws Exception
	{
		FeesStructure fees=FeesStructureDao.getFeesStructure(year,branch);
		if(fees!=null)
		{
			return fees;
		}
		else
		{
			throw new Exception("no records found");
		}
		
	}
	
	
	public List<Transaction> getAllTransactions(String email) throws Exception
	{
		List<Transaction> transactions=MyTransactionDetailsDao.findMyTransactionDetails(email);
		System.out.println(email);
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
