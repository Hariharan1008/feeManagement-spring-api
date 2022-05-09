package com.feemanagementapp.service;

import java.util.Scanner;

public class HostelCheck {
	static Scanner sc=new Scanner(System.in);
	public static int hostelCheck()
	{
		System.out.println("Are you an Hostellar?");
		 System.out.println("1) Yes");
		 System.out.println("2) No");
		 int studentType=sc.nextInt();
		 int hostellarOrDayScholar=0;
		 if(studentType==1)
		 {
			 hostellarOrDayScholar=1;
		 }
		 else if(studentType==2)
		 {
			 hostellarOrDayScholar=dayScholarCheck(); 
		 }
		 else 
		 {
			 System.out.println("enter only valid option");
			 hostelCheck();
		 }
		 return hostellarOrDayScholar;
	}
	public static int dayScholarCheck()
	{
		System.out.println("Are you an dayscholar?");
		 System.out.println("1) Yes");
		 System.out.println("2) No");
		 int dayScholar=sc.nextInt();
		 int needBus=busCheck();
		 if(needBus==1)
		 {
		    return 3;
		 }
		 else
		 {
			 return 2;
		 }
	}
	public static int busCheck()
	{
		System.out.println("Do you need an college bus?");
		 System.out.println("1) Yes");
		 System.out.println("2) No");
		 int bus=sc.nextInt();
		 if(bus==1)
		 {
			 return 1;
		 }
		 else
		 {
			 return 0;
		 }
	}


}
