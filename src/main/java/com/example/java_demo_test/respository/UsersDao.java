package com.example.java_demo_test.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.Users;

@Repository
public interface UsersDao extends JpaRepository<Users, Integer>{
	
	//�ݭn�f�t SET GLOBAL information_schema_stats_expiry=0;
	@Query(value = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES "
			+ "WHERE TABLE_NAME = 'users' AND TABLE_SCHEMA = 'java_demo_test'", 
			nativeQuery = true)
	public Integer selectLastInsertId();
	
	@Query(value = "SELECT MAX(id) from users", nativeQuery = true)
	public Integer selectMaxId();

}
