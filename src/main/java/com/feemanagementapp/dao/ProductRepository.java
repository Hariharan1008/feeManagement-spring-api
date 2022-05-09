package com.feemanagementapp.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.feemanagementapp.model.Products;
@Repository
public interface ProductRepository extends JpaRepository<Products,Integer>{
   @Query("select u from com.feemanagementapp.model.Products u where u.id=:id")
   public Products getProductById(@Param("id") int id);
   
   @Query("select u from com.feemanagementapp.model.Products u where u.brand=:brand")
   List<Products> getProductsByBrand(@Param("brand") String brand);
   
   @Query("select u from com.feemanagementapp.model.Products u where u.category=:category")
   List<Products> getProductsByCategory(@Param("category") String category);
   
   @Transactional
   @Modifying
   @Query("update com.feemanagementapp.model.Products u set u.walletPoints=:points where u.id=:id")
   void updateProductById(@Param("points") int points,@Param("id") int id);
}
