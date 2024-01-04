package com.suresh.TimeSheetApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suresh.TimeSheetApp.entity.AddNewProjectManager;
@Repository
public interface AddNewProjectRepository extends JpaRepository<AddNewProjectManager, Integer>{

}
