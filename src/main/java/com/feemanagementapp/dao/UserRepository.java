package com.feemanagementapp.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.feemanagementapp.model.Registration;

public interface UserRepository extends JpaRepository<Registration, Integer>  {
  @Query("select u.userMobileNumber from com.feemanagementapp.model.Registration u where u.userEmail=:email")
  public long getMobileByEmail(@Param("email") String email);
  
  @Query("select u.userEmail from com.feemanagementapp.model.Registration u where u.userEmail=:email")
  public String getEmail(@Param("email") String email);
  
  @Transactional
  @Modifying
  @Query("update com.feemanagementapp.model.Registration u set u.userPassword=:password where u.userEmail=:email")
  void updatePassword(@Param("password") String password,@Param("email") String email);
} 
