package com.feemanagementapp.controller;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.feemanagementapp.dao.AllProductTransactions;
import com.feemanagementapp.dao.FindTicketUsingIdDao;
import com.feemanagementapp.dao.MyTicketDetailsDao;
import com.feemanagementapp.dao.MyTransactionDetailsDao;
import com.feemanagementapp.dao.TransactionDetailsUsingIdDao;
import com.feemanagementapp.dao.WalletRepository;
import com.feemanagementapp.dao.WalletTransactionRepository;
import com.feemanagementapp.model.FundTransaction;
import com.feemanagementapp.model.Message;
import com.feemanagementapp.model.MyProductTransaction;
import com.feemanagementapp.model.Tickets;
import com.feemanagementapp.model.Transaction;
import com.feemanagementapp.model.WalletTransaction;
import com.feemanagementapp.service.FeeService;
import com.feemanagementapp.service.TicketService;
import com.feemanagementapp.service.WalletService;



@RestController
public class TransactionController {
	@Autowired
	TicketService ticketService;
	
	@Autowired
	WalletRepository wallet;
	
	@Autowired
	WalletTransactionRepository walletTransactionRepository;
	
	@Autowired
	FeeService feeService;
	
	@Autowired
	WalletService walletService;
	
	@GetMapping("transaction/allTickets")
	public ResponseEntity<?> getAllTickets(@RequestParam("email") String email) throws ClassNotFoundException, SQLException
	{
		try
		{
			List<Tickets> tickets=ticketService.getAllTickets(email);
			if(tickets!=null)
			{
				return new ResponseEntity<>(tickets,HttpStatus.OK);
			}
			else
			{
				Message message=new Message();
				message.setMessage("cant process your request at this time");
				return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
			}
		}
		catch(Exception e)
		{
			Message message=new Message();
			message.setMessage(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("transaction/ticket/usingid")
	public ResponseEntity<?> findTicketUsingId(@RequestParam("ticketId") int ticketId,@RequestParam("email") String email) throws ClassNotFoundException, SQLException
	{
		try
		{
			Tickets ticket=ticketService.getTicketUsingId(ticketId, email);
			if(ticket!=null)
			{
				return new ResponseEntity<>(ticket,HttpStatus.OK);
			}
			else
			{
				Message message=new Message();
				message.setMessage("cant process your request at this time");
				return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
			}
		}
		catch(Exception e)
		{
			Message message=new Message();
			message.setMessage(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("transaction/feestransactions")
	public ResponseEntity<?>  getAllTransactionDetails(@RequestParam("sessionEmail") String email) throws ClassNotFoundException, SQLException
	{
		try
		{
		   FeeService feeService=new FeeService();
		    List<Transaction> transactions =feeService.getAllTransactions(email);
		    return new ResponseEntity<>(transactions,HttpStatus.OK);
		}
		catch(Exception e)
		{
			Message message=new Message();
			message.setMessage(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("transaction/feestransaction/id")
	public ResponseEntity<?> getTransactionDetailsUsingId(@RequestParam("transactionId") int transactionId,@RequestParam("email") String email) throws ClassNotFoundException, SQLException
	{
	   try {
		    Transaction transaction=feeService.getTransactionUsingId(transactionId, email);
		    return new ResponseEntity<>(transaction,HttpStatus.OK);
	   }
	   catch(Exception e)
		{
			Message message=new Message();
			message.setMessage(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("transaction/fund/allTransactions")
	public List<FundTransaction> getAllWalletTransaction(@RequestParam("mobile") long mobile)
	{
		
		List<FundTransaction> transactions=wallet.findByMobile(mobile);
		return transactions;
	}
	
	@GetMapping("transaction/wallet/findTransaction/{id}")
	public FundTransaction findUsingId(@PathVariable("id") int transactionId)
	{
		FundTransaction transaction=wallet.findByTransactionId(transactionId);
		return transaction;
	}
	
	@GetMapping("transaction/product/allTransactions")
	public List<MyProductTransaction> getAllProductTransactions(@RequestParam("mobile") long mobile) throws ClassNotFoundException, SQLException
	{
		AllProductTransactions transaction=new AllProductTransactions();
		List<MyProductTransaction> transactions=transaction.getAllProductTransactions(mobile);
		if(transactions!=null)
		{
			return transactions;
		}
		else
		{
			return null;
		}
	}
	@GetMapping("transaction/wallet/allTransactions")
	public ResponseEntity<?> getAllWalletTransactions(@RequestParam("mobile") long mobile) throws ClassNotFoundException, SQLException
	{
		
		try
		{
			List<WalletTransaction> transactions=walletService.getAllWalletTransactions(mobile);
			if(transactions!=null)
			{
				return new ResponseEntity<>(transactions,HttpStatus.OK);
			}
			else
			{
				Message message=new Message();
				message.setMessage("cant process your request at this time");
				return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
			}
		}
		catch(Exception e)
		{
			Message message=new Message();
			message.setMessage(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
		
	}
	

}
