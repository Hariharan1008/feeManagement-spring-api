package com.feemanagementapp.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name="fund_transactions")
public class FundTransaction {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="transaction_id")
	private int transactionId;
	
	@Column(name="sender")
	private long sender;
	
	@Column(name="receiver")
	private long receiver;
	
	@Column(name="amount")
	private long amount;
	
	@Column(name="transfered_on")
	private Date transferedOn;

}
