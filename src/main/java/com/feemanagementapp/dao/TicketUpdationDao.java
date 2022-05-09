package com.feemanagementapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.feemanagementapp.model.Tickets;




public class TicketUpdationDao {
	public static List<Tickets> findAllTickets() throws ClassNotFoundException, SQLException
	{
		List<Tickets> list=new ArrayList<Tickets>();
		Connection connection=ConnectionUtil.databaseConnection();
		PreparedStatement statement;
		ResultSet result=null;
		String query="select id,Queries,Created_On,Ticket_Status from tickets";
		statement=connection.prepareStatement(query);
		result=statement.executeQuery();
		Tickets ticket=null;
		int id=0;
		String myQuery=null;
		Date date=null;
		String status=null;
		while(result.next())
		{
			ticket=new Tickets();
			id=result.getInt("id");
			myQuery=result.getString("Queries");
			date=result.getDate("Created_On");
			status=result.getString("Ticket_Status");
			ticket.setTicketId(id);
			ticket.setMyQuery(myQuery);
			ticket.setCreatedOn(date);
			ticket.setTicketStatus(status);
			list.add(ticket);
		}
		connection.close();
		return list;
	}
	public static void updateTicket(int id) throws ClassNotFoundException, SQLException
	{
		Connection connection=ConnectionUtil.databaseConnection();
		PreparedStatement statement=null;
		String query="update tickets set Ticket_Status=? where id=?";
		statement=connection.prepareStatement(query);
		statement.setString(1,"Resolved");
		statement.setInt(2,id);
		int rows = statement.executeUpdate();
		connection.close();
	}


}
