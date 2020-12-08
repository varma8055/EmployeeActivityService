package com.employeeactivity.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeeactivity.dto.UpdateEmployeeActivityDto;
import com.employeeactivity.entity.EmployeeActivity;
import com.employeeactivity.exceptions.ActivityIdNotFoundException;
import com.employeeactivity.repository.EmployeeActivityRepository;
import com.employeeactivity.service.UpdateEmployeeActivityService;


@Service
public class UpdateEmployeeActivityServiceImpl implements UpdateEmployeeActivityService {
	private static final Logger logger = LoggerFactory.getLogger(UpdateEmployeeActivityServiceImpl.class);
	
	@Autowired
	EmployeeActivityRepository employeeActivityRepository;

	@Override
	public void updateEmployeeActivity(UpdateEmployeeActivityDto updateEmployeeActivityDto) throws Exception {
		Optional<EmployeeActivity> employee =employeeActivityRepository.findById(updateEmployeeActivityDto.getId());
		if(!employee.isPresent()) {
			logger.warn("No Records found for that Activity Id");
			throw new ActivityIdNotFoundException("Activity id not found");
		}
			logger.info("The Status of the Actitivies successfully Updated.");
		EmployeeActivity employeeActivity=employee.get();
		employeeActivity.setActivityStatus(updateEmployeeActivityDto.getActivityStatus());
		employeeActivity.setActivityDate(LocalDate.now());
		employeeActivityRepository.save(employeeActivity);

	}

}
