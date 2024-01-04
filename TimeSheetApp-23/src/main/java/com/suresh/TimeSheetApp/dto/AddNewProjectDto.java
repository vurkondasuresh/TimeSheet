package com.suresh.TimeSheetApp.dto;

import lombok.Data;

@Data
public class AddNewProjectDto {
	private String timeCardId;
	private String projectName;
	private String projectManagerName;
	private String location;
	private Integer monHours;
	private Integer tueHours;
	private Integer wedHours;
	private Integer thuHours;
	private Integer friHours;
	private Integer satHours;
	private Integer sunHours;
	private Integer totalHours;
	private String comment;

}
