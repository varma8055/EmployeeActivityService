package com.employeeactivity.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeActivityDto {
	
	
	@Column(name = "employee_code")
	private Long employeeCode;
	
	
	
	@NotEmpty(message = "Activity description required")
	@Size(min =5 , max=200,message = "Please provide Activity Description")
	@Column(name="activity_description")
	private String activityDescription;
	
	
	
	@NotEmpty(message = "Activity Status required")
	@Size(min =5 , max=200,message = "Please provide Activity status")
	@Column(name="activity_status")
	private String activityStatus;
	
	public Long getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(Long employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getActivityDescription() {
		return activityDescription;
	}
	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}
	
	public String getActivityStatus() {
		return activityStatus;
	}
	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}
	
	

}
