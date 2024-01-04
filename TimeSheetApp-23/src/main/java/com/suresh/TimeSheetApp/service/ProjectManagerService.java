package com.suresh.TimeSheetApp.service;

import java.util.List;

import com.suresh.TimeSheetApp.dto.AddNewProjectDto;
import com.suresh.TimeSheetApp.entity.AddNewProjectManager;

public interface ProjectManagerService {

	public AddNewProjectManager addNewProject(AddNewProjectDto form);
	List<AddNewProjectDto> getAllData();

}
