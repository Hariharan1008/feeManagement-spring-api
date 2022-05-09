package com.feemanagementapp.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feemanagementapp.dao.TicketsRepository;
import com.feemanagementapp.model.Tickets;
@Component
public class TicketInsertion {
	
	@Autowired
	TicketsRepository ticketRepository;
	
	static int ticketId=145312;
	
	public int insertTicket(String query,String email)
	{
		Tickets ticket=new Tickets();
		ticket.setMyQuery(query);
		ticket.setTicketStatus("pending");
		ticket.setEmail(email);
		ticket.setCreatedOn(Date.valueOf(LocalDate.now()));
		ticket.setTicketId(getTicketId());
		ticketRepository.save(ticket);
		return 1;
		
	}
	public static int getTicketId()
	{
		ticketId++;
		int b = (int)(Math.random()*(5000-8000+1)+5000);
		return ticketId+b;
	}
	
	public List<Tickets> getAllTicketsUsingEmail()
	{
		String status="pending";
		List<Tickets> tickets=ticketRepository.getPendingTickets(status);
		return tickets;
	}

}
