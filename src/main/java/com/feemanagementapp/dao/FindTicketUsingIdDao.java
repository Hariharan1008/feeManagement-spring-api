package com.feemanagementapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.feemanagementapp.model.Tickets;






public class FindTicketUsingIdDao {
	public static Tickets usingTicketId(int ticketId) throws ClassNotFoundException, SQLException
	{
		Connection connection=ConnectionUtil.databaseConnection();
		PreparedStatement statement=null;
		String qurey="SELECT * FROM Tickets WHERE ticket_Id=?";
		statement=connection.prepareStatement(qurey);
		statement.setInt(1, ticketId);
		ResultSet result=statement.executeQuery();
		int ticketIn=0;
		String myQuery=null;
		Date date=null;
		Tickets ticket=null;
		String ticketStatus=null;
		String name=null;
		if(result.next())
		{
			ticket=new Tickets();
			ticketId=result.getInt("Ticket_Id");
			//name=result.getString("Name");
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
