package com.feemanagementapp.model;

public class Session {
	public static String sessionEmail;
	public static String sessionmobile;
	public static void setSessionEmail(String email) {
		sessionEmail=email;
	}
	public static String getSessionEmail()
	{
		return sessionEmail;
	}
	public static void setSessionmobile(String mobile) {
		sessionmobile=mobile;
	}
	public static String getSessionmobile() {
		return sessionmobile;
	}

}
