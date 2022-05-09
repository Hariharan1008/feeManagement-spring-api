package com.feemanagementapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="fees_structure")
public class Fees {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
    
	@Column(name="Year")
	private int year;
	
	@Column(name="Dept")
	private String dept;
	
	@Column(name="First_Semester_Fees")
	private String firstSemesterFees;
	
	@Column(name="Second_Semester_Fees")
	private String secondSemesterFees;
	
	@Column(name="Hostel_Fees")
	private String hostelFees;
	
	@Column(name="Transport_Fees")
	private String transportFees;
	
	
	 
	
	

}
