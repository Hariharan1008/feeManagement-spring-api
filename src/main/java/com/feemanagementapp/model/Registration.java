package com.feemanagementapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="fees_user")
public class Registration {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="User_Name")
	private String userName;
	
	@Column(name="User_Mobile")
	private String userMobileNumber;
	
	@Column(name="User_Age")
	private int age;
	
	@Column(name="user_Email")
	private String userEmail;
	
	@Column(name="User_Password")
	private String userPassword;
	
	@Column(name="Pursuing_Year")
	private int yearOfStudy;
	
	@Column(name="User_Dept")
	private String branch;
	
	@Column(name="H_or_D")
	private String hOrD;
	
	@Column(name="Bus")
	private String needBus;
	
	public String gethOrD() {
		return hOrD;
	}
	
	public void sethOrD(String hOrD) {
		this.hOrD = hOrD;
	}
	public String getNeedBus() {
		return needBus;
	}
	public void setNeedBus(String needBus) {
		this.needBus = needBus;
	}
	private static String sessionMail;
//	public Registration(String name,String mobile)
//	{
//		this.name=name;
//		this.userMobileNumber=mobile;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserMobileNumber() {
		return userMobileNumber;
	}
	public void setUserMobileNumber(String userMobileNumber) {
		this.userMobileNumber = userMobileNumber;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public int getYearOfStudy() {
		return yearOfStudy;
	}
	public void setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public static String getSessionMail() {
		return sessionMail;
	}
	public static void setSessionMail(String sessionMail) {
		Registration.sessionMail = sessionMail;
	}

}
