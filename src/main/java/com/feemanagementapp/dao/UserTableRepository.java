package com.feemanagementapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feemanagementapp.model.UserTable;

public interface UserTableRepository extends JpaRepository<UserTable, Integer> {

}
