package com.suresh.TimeSheetApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suresh.TimeSheetApp.dto.AddNewProjectDto;
import com.suresh.TimeSheetApp.entity.AddNewProjectManager;
import com.suresh.TimeSheetApp.repository.AddNewProjectRepository;
import com.suresh.TimeSheetApp.util.TimeCardId;

@Service
public class ProjectManagerServiceImp implements ProjectManagerService{
	@Autowired
	private AddNewProjectRepository repo;
	
	@Autowired
	private TimeCardId timeCardId;
	

	
	@Override
	public AddNewProjectManager addNewProject(AddNewProjectDto form) {
		if(form!=null)
		{
			AddNewProjectManager entity =new AddNewProjectManager();
			BeanUtils.copyProperties(form, entity);
			entity.setTimeCardId(timeCardId.timeCardId());
			entity.setTotalHours(form.getMonHours()+form.getTueHours()+form.getWedHours()+form.getThuHours()+form.getFriHours()+form.getSatHours()+form.getSunHours());
			repo.save(entity);
			return entity;
		}
		return null;
		
	}
	
	@Override
    public List<AddNewProjectDto> getAllData() {
        List<AddNewProjectManager> entities = repo.findAll();
        List<AddNewProjectDto> dtos = new ArrayList<>();

        for (AddNewProjectManager entity : entities) {
            AddNewProjectDto dto = new AddNewProjectDto();
            BeanUtils.copyProperties(entity, dto);
            dtos.add(dto);
        }

        return dtos;
    }
	
	

}
