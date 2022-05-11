package com.feemanagementapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.feemanagementapp.model.Tickets;






public class FindTicketUsingIdDao {
	public static Tickets usingTicketId(int ticketId,String email) throws ClassNotFoundException, SQLException
	{
		Connection connection=ConnectionUtil.databaseConnection();
		PreparedStatement statement=null;
		String qurey="SELECT * FROM tickets WHERE ticket_Id=? and Email=?";
		statement=connection.prepareStatement(qurey);
		statement.setInt(1, ticketId);
		statement.setString(2, email);
		ResultSet result=statement.executeQuery();
		
		String myQuery=null;
		Date date=null;
		Tickets ticket=null;
		String ticketStatus=null;
		
		if(result.next())
		{
			ticket=new Tickets();
			ticketId=result.getInt("Ticket_Id");
			
			myQuery=result.getString("Queries");
			date=result.getDate("Created_On");
			ticketStatus=result.getString("Ticket_Status");
			ticket.setTicketId(ticketId);
//			ticket.setName(name);
			ticket.setMyQuery(myQuery);
			ticket.setCreatedOn(date);
			ticket.setTicketStatus(ticketStatus);
		}
		connection.close();
		return ticket;
	}

	
}
