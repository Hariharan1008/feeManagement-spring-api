package com.feemanagementapp.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.feemanagementapp.model.Fees;
@Repository
public interface FeesUpdationRepository extends JpaRepository<Fees,Integer> {
//	@Transactional
//	@Modifying
////	@Query("update com.feemanagementapp.model.Fees u set u.firstSemesterFees=:firstSemester where u.year=:year and u.department=:branch")
////	Integer updateByYear(@Param("firstSemester") String fisrtSemester,@Param("year") int id,@Param("branch") String branch);

}
