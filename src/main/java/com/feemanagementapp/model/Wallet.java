package com.feemanagementapp.model;

import java.sql.Date;

public class Wallet {
	private int walletPoints;
	public int getWalletPoints() {
		return walletPoints;
	}
	public void setWalletPoints(int walletPoints) {
		this.walletPoints = walletPoints;
	}
	private long totalFees;
	private long pendingFees;
	private long feesPaid;
	public long getTotalFees() {
		return totalFees;
	}
	public void setTotalFees(long totalFees) {
		this.totalFees = totalFees;
	}
	public long getPendingFees() {
		return pendingFees;
	}
	public void setPendingFees(long pendingFees) {
		this.pendingFees = pendingFees;
	}
	public long getFeesPaid() {
		return feesPaid;
	}
	public void setFeesPaid(long feesPaid) {
		this.feesPaid = feesPaid;
	}
	private long mobile;
	private long amount;
	private Date date;
	private String operation;
	private long balance;
	private String password;
	private int tpin;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTpin() {
		return tpin;
	}
	public void setTpin(int tpin) {
		this.tpin = tpin;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
