package com.feemanagementapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.feemanagementapp.model.WalletTransaction;
@Repository
public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Integer> {
	
	@Query("select u from com.feemanagementapp.model.WalletTransaction u where u.mobile=:mobile")
	List<WalletTransaction> getAllWalletTransactions(@Param("mobile") long mobile);

}
