package com.feemanagementapp.model;

import java.sql.Date;

public class ProductTransaction {
   private int productId;
   private Date purchasedOn;
   private long mobile;
public int getProductId() {
	return productId;
}
public void setProductId(int productId) {
	this.productId = productId;
}
public Date getPurchasedOn() {
	return purchasedOn;
}
public void setPurchasedOn(Date purchasedOn) {
	this.purchasedOn = purchasedOn;
}
public long getMobile() {
	return mobile;
}
public void setMobile(long mobile) {
	this.mobile = mobile;
}
}
