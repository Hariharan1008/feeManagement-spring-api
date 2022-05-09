package com.feemanagementapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.feemanagementapp.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification,Integer> {
      
	@Query("select u from com.feemanagementapp.model.Notification u where u.mobile=:mobile order by u.id desc")
	public List<Notification> findAllNotificationsByMobile(@Param("mobile") long mobile);
}
