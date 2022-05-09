package com.feemanagementapp.controller;



import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feemanagementapp.dao.TicketUpdationDao;
import com.feemanagementapp.dao.UserRepository;
import com.feemanagementapp.model.Tickets;
import com.feemanagementapp.service.NotificationService;
import com.feemanagementapp.service.TicketInsertion;

@RestController
public class TicketController {
 @Autowired
 TicketInsertion ticketInsertion; 
 
 @Autowired
 NotificationService notificationService;
 
 @Autowired
 UserRepository userRepository;
 
 
 
	
	@GetMapping("ticket/insert")
	public String insertTicket(@RequestParam("query") String query,@RequestParam("email") String email)
	{
		System.out.println(query);
		if(query!=null && query.trim()!="" && query.trim()!=" ")
		{
			
			int inserted=ticketInsertion.insertTicket(query, email);
			if(inserted==1)
			{
				return "success";
			}
			else
			{
				return "failed";
			}
		}
		else
		{
			return "ticket cannot be null";
		}
	}
	
	@GetMapping("ticket/update")
	public String updateTicket(@RequestParam("id") int id,@RequestParam("query") String query,@RequestParam("email") String email) throws ClassNotFoundException, SQLException
	{
		TicketUpdationDao.updateTicket(id);
		String message="your query got resolved"+"("+query+")";
		long mobile=userRepository.getMobileByEmail(email);
		notificationService.notificationInserter(message, mobile);
		return "success";
		
	}
	
	@GetMapping("ticket/get/all")
	public List<Tickets> getAllTickets()
	{
		List<Tickets> tickets=ticketInsertion.getAllTicketsUsingEmail();
		return tickets;
	}
	
}
