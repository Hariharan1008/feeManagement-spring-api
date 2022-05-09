package com.feemanagementapp.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.feemanagementapp.model.Tickets;
@Repository
public interface TicketsRepository extends JpaRepository<Tickets, Integer>{
	
	@Query("select u from com.feemanagementapp.model.Tickets u where u.email=:email")
	public List<Tickets> FindAllTicketsByEmail(@Param("email") String email);
	
	@Query("select u from com.feemanagementapp.model.Tickets u where u.ticketStatus=:status")
	List<Tickets> getPendingTickets(@Param("status") String status);

}
