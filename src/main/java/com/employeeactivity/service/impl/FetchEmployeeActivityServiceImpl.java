package com.employeeactivity.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.employeeactivity.dto.EmployeeActivityResponseDto;
import com.employeeactivity.entity.EmployeeActivity;
import com.employeeactivity.exceptions.EmployeeActivitiesNotFoundException;
import com.employeeactivity.repository.EmployeeActivityRepository;
import com.employeeactivity.service.FetchEmployeeActivityService;

@Service
public class FetchEmployeeActivityServiceImpl implements  FetchEmployeeActivityService {
	private static final Logger logger = LoggerFactory.getLogger(FetchEmployeeActivityServiceImpl.class);
	@Autowired
	EmployeeActivityRepository employeeActivityRepository;

	@Override
	public List<EmployeeActivityResponseDto> getEmployeeActivity(Long employeeCode, int pageNumber, int pageSize) throws Exception {
		Page<EmployeeActivity> employeeActivity;
		List<EmployeeActivityResponseDto> employeeActivityResponseDtoList=new ArrayList<EmployeeActivityResponseDto>();
		
		
		Pageable pageable=PageRequest.of(pageNumber, pageSize);
		employeeActivity=employeeActivityRepository.findByEmployeeCode(employeeCode,pageable);
		if(employeeActivity.isEmpty()) {
			logger.warn("Employee Activites not Found");
			throw new EmployeeActivitiesNotFoundException("Employee Activites not Found");
		}
			logger.info("Get the Employee Activities and its Status");
		employeeActivity.stream().forEach(employee->{
			EmployeeActivityResponseDto employeeActivityDto=new EmployeeActivityResponseDto();
			BeanUtils.copyProperties(employee, employeeActivityDto);
			employeeActivityResponseDtoList.add(employeeActivityDto);
		});
		return employeeActivityResponseDtoList;
	}

	

}
