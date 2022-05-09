package com.feemanagementapp.model;

import java.sql.Date;

public class FeesStructure {
	private String name;
	private Date paidOn;
	private String firstSemesterFees;
	private String SecondSemesterFees;
	private String hotelFees;
	private String transportFees;
	private String totalFees;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getPaidOn() {
		return paidOn;
	}
	public void setPaidOn(Date paidOn) {
		this.paidOn = paidOn;
	}
	private String feesPaid;
	private String feesPending;
	private String email;
	private String payingAmount;
	
	public String getPayingAmount() {
		return payingAmount;
	}
	public void setPayingAmount(String payingAmount) {
		this.payingAmount = payingAmount;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTotalFees() {
		return totalFees;
	}
	public void setTotalFees(String totalFees) {
		this.totalFees = totalFees;
	}
	public String getFeesPaid() {
		return feesPaid;
	}
	public void setFeesPaid(String feesPaid) {
		this.feesPaid = feesPaid;
	}
	public String getFeesPending() {
		return feesPending;
	}
	public void setFeesPending(String feesPending) {
		this.feesPending = feesPending;
	}
	public String getFirstSemesterFees() {
		return firstSemesterFees;
	}
	public void setFirstSemesterFees(String firstSemesterFees) {
		this.firstSemesterFees = firstSemesterFees;
	}
	public String getSecondSemesterFees() {
		return SecondSemesterFees;
	}
	public void setSecondSemesterFees(String secondSemesterFees) {
		SecondSemesterFees = secondSemesterFees;
	}
	public String getHotelFees() {
		return hotelFees;
	}
	public void setHotelFees(String hotelFees) {
		this.hotelFees = hotelFees;
	}
	public String getTransportFees() {
		return transportFees;
	}
	public void setTransportFees(String transportFees) {
		this.transportFees = transportFees;
	}
	
	
	

}
