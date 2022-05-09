package com.feemanagementapp.service;

import com.feemanagementapp.dao.NotificationRepository;
import com.feemanagementapp.model.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class NotificationService {
	@Autowired
	NotificationRepository notificationRepository;
	
   public int notificationInserter(String message,long mobile)
   {
	   Notification notification =new Notification();
	   notification.setMessage(message);
	   notification.setMobile(mobile);
	   notification.setStatus("not");
	   notificationRepository.save(notification);
	   return 1;
	   
   }
}
