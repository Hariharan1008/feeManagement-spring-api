package com.feemanagementapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feemanagementapp.dao.NotificationRepository;
import com.feemanagementapp.model.Notification;

@RestController
public class NotificationController {
	@Autowired
	NotificationRepository notificationRepository;
	
	@GetMapping("notification/get/all")
	public List<Notification> getAllNotifications(@RequestParam("mobile") long mobile)
	{
		List<Notification> notifications=notificationRepository.findAllNotificationsByMobile(mobile);
		return notifications;
	}

}
