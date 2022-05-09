package com.feemanagementapp.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="wallet_transactions")
public class WalletTransaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="amount")
	private long amount;
	
	@Column(name="operation")
	private String operation;
	
	@Column(name="performed_on")
	private Date performedOn;
	
	@Column(name="mobile_no")
	private long mobile;
	

}
