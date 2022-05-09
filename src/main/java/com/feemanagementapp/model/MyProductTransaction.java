package com.feemanagementapp.model;

import java.sql.Date;

public class MyProductTransaction {
   private String productName;
   private String userName;
   private int walletPoints;
   private Date purchasedOn;
   private String image;
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public int getWalletPoints() {
	return walletPoints;
}
public void setWalletPoints(int price) {
	this.walletPoints = price;
}
public Date getPurchasedOn() {
	return purchasedOn;
}
public void setPurchasedOn(Date purchasedOn) {
	this.purchasedOn = purchasedOn;
}
   
}
