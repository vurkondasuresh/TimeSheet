package com.suresh.TimeSheetApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suresh.TimeSheetApp.entity.User;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);

	public User findByName(String name);
	
	List<User> findByRoles_Name(String roleName);
	
}
