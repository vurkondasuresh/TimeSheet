package com.suresh.TimeSheetApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.suresh.TimeSheetApp.dto.AddNewProjectDto;
import com.suresh.TimeSheetApp.entity.AddNewProjectManager;
import com.suresh.TimeSheetApp.service.ProjectManagerService;

@RestController
@CrossOrigin("*")
public class AddNewController {
	
	@Autowired
	private ProjectManagerService service;
	
	@PostMapping("/save")
	public AddNewProjectManager addNewProject (@RequestBody AddNewProjectDto form)
	{
		return service.addNewProject(form);
	}
	
	@GetMapping("/getAlldata")
    public List<AddNewProjectDto> getAllData() {
        return service.getAllData();
    }

}
