package com.feemanagementapp.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name="tickets")
public class Tickets {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
    @Column(name="Ticket_Id")
	private int ticketId;
    
    @Column(name="Queries")
	private String myQuery;
    
    @Column(name="Created_On")
	private Date createdOn;
    
    @Column(name="Email")
	private String email;
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="Ticket_Status")
	private String ticketStatus;
	
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMyQuery() {
		return myQuery;
	}
	public void setMyQuery(String ticket) {
		this.myQuery = ticket;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date date) {
		this.createdOn = date;
	}
	public String getTicketStatus() {
		return ticketStatus;
	}
	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
	
	

}
