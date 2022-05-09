package com.feemanagementapp.service;

import java.sql.SQLException;

import com.feemanagementapp.dao.FindWalletPoints;
import com.feemanagementapp.dao.UpdateWalletPointsDao;



public class VerifyWalletPointsAndUpdate {
   public int verifyPoints(int productId,long mobile) throws ClassNotFoundException, SQLException
   {
	   FindWalletPoints points=new FindWalletPoints();
	   int productPoints=points.getProductPoints(productId);
	   int userPoints=points.getWalletPoints(mobile);
	   if(userPoints>=productPoints)
	   {
		   return 1;
	   }
	   else
	   {
		   return 0;
	   }
			   
   }
   public int updateWalletPoints(int productId,long mobile) throws ClassNotFoundException, SQLException
   {
	   FindWalletPoints points=new FindWalletPoints();
	   int productPoints=points.getProductPoints(productId);
	   int userPoints=points.getWalletPoints(mobile);
	   int updatedPoints=userPoints-productPoints;
	   UpdateWalletPointsDao update=new UpdateWalletPointsDao();
	   int updated=update.updateMyWallet(mobile, updatedPoints);
	   return updated;
   }
}
