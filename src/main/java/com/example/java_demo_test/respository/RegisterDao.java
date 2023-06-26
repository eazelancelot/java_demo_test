package com.example.java_demo_test.respository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.Register;

@Repository
public interface RegisterDao extends JpaRepository<Register, String>{

	public Register findByAccountAndPwd(String account, String pwd);
	
	public Register findByAccountAndPwdAndActive(String account, String pwd, boolean active);
	
	public Page<Register> findAll(Pageable pageable);
	public Page<Register> findAllByActive(boolean isActive, Pageable pageable);
}
