package com.feemanagementapp.model;

import java.sql.Date;


public class FundTransfer {
	private String transactionId;
	private long senderMobile;
	private long receiverMobile;
	private long senderBalance;
	private long receiverBalance;
	private long amount;
	private Date transferedOn;
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public long getSenderMobile() {
		return senderMobile;
	}
	public void setSenderMobile(long senderMobile) {
		this.senderMobile = senderMobile;
	}
	public long getReceiverMobile() {
		return receiverMobile;
	}
	public void setReceiverMobile(long receiverMobile) {
		this.receiverMobile = receiverMobile;
	}
	public long getSenderBalance() {
		return senderBalance;
	}
	public void setSenderBalance(long senderBalance) {
		this.senderBalance = senderBalance;
	}
	public long getReceiverBalance() {
		return receiverBalance;
	}
	public void setReceiverBalance(long receiverBalance) {
		this.receiverBalance = receiverBalance;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public Date getTransferedOn() {
		return transferedOn;
	}
	public void setTransferedOn(Date transferedOn) {
		this.transferedOn = transferedOn;
	}
	

}
