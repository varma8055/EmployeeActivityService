package com.employeeactivity.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeeactivity.dto.EmployeeActivityDto;
import com.employeeactivity.entity.EmployeeActivity;
import com.employeeactivity.repository.EmployeeActivityRepository;
import com.employeeactivity.service.AddEmployeeActivityService;

@Service
public class AddEmployeeActivityServiceImpl implements AddEmployeeActivityService {
	private static final Logger logger = LoggerFactory.getLogger(AddEmployeeActivityServiceImpl.class);
	
	@Autowired
	EmployeeActivityRepository employeeActivityRepository;

	@Override
	public void addEmployeeActivity(List<EmployeeActivityDto> employeeActivityDto) {
		List<EmployeeActivity> employeeList=new ArrayList<EmployeeActivity>();
		
		employeeActivityDto.stream().forEach(employeeDto->{
			EmployeeActivity employeeActivity = new EmployeeActivity();
			BeanUtils.copyProperties(employeeDto,employeeActivity);
			LocalDateTime localDateTime=LocalDateTime.now();
			employeeActivity.setActivityDate(localDateTime.toLocalDate());
			employeeList.add(employeeActivity);
		});
		
		employeeActivityRepository.saveAll(employeeList);
		logger.info("Employee activities Added Successfully");
		

	}

}
