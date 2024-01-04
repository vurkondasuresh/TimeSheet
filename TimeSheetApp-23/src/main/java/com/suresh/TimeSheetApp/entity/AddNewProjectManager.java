package com.suresh.TimeSheetApp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name ="WORKSHEET_TBL")
public class AddNewProjectManager {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
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
