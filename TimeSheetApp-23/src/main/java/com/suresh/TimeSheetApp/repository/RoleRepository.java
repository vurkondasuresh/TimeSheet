package com.suresh.TimeSheetApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suresh.TimeSheetApp.entity.Role;
import com.suresh.TimeSheetApp.entity.User;

@Repository

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);
	
}
