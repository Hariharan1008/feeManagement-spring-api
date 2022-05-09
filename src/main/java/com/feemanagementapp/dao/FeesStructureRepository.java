package com.feemanagementapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feemanagementapp.model.Fees;

@Repository
public interface FeesStructureRepository extends JpaRepository<Fees,Integer>{
   
}
