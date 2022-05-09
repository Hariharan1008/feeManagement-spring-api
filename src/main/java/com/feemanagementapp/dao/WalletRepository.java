package com.feemanagementapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.feemanagementapp.model.FundTransaction;


@Repository
public interface WalletRepository extends JpaRepository<FundTransaction,Integer> {
	
 
	@Query("select u from com.feemanagementapp.model.FundTransaction u where u.sender =:mobile")
	List<FundTransaction> findByMobile(@Param("mobile") long mobile);
	
	
	@Query("select u from com.feemanagementapp.model.FundTransaction u where u.transactionId=:transactionId")
	FundTransaction findByTransactionId(@Param("transactionId") int transactionId );

}
