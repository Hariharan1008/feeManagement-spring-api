package com.feemanagementapp.controller;



import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feemanagementapp.dao.TicketUpdationDao;
import com.feemanagementapp.dao.UserRepository;
import com.feemanagementapp.model.Message;
import com.feemanagementapp.model.Tickets;
import com.feemanagementapp.service.NotificationService;
import com.feemanagementapp.service.TicketService;

@RestController
public class TicketController {
 @Autowired
 TicketService ticketInsertion; 
 
 @Autowired
 NotificationService notificationService;
 
 @Autowired
 UserRepository userRepository;
 
 
 
	
	@GetMapping("ticket/insert")
	public ResponseEntity<?> insertTicket(@RequestParam("query") String query,@RequestParam("email") String email)
	{
		System.out.println(query);
		if(query!=null && query.trim()!="" && query.trim()!=" ")
		{
			try {
				int inserted=ticketInsertion.insertTicket(query, email);
				return new ResponseEntity<>(HttpStatus.OK);
			}
			catch(Exception e)
			{
				Message message = new Message();
				message.setMessage(e.getMessage());
				return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
			}
		}
		else
		{
			Message message = new Message();
			message.setMessage("query cannot be empty");
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
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
