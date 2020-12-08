package com.employeeactivity.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeeactivity.entity.EmployeeActivity;
import com.employeeactivity.exceptions.ActivityIdNotFoundException;
import com.employeeactivity.repository.EmployeeActivityRepository;
import com.employeeactivity.service.RemoveEmployeeActivityService;

@Service
public class RemoveEmpoyeeActivityServiceImpl implements RemoveEmployeeActivityService {
	private static final Logger logger = LoggerFactory.getLogger(RemoveEmpoyeeActivityServiceImpl.class);
	@Autowired
	EmployeeActivityRepository employeeActivityRepository;

	@Override
	public void removeEmployeeActivity(Long id) throws ActivityIdNotFoundException {
		Optional<EmployeeActivity> employeeActivity=employeeActivityRepository.findById(id);
		if(!employeeActivity.isPresent()) {
			logger.warn("No Records found for that Activity Id");
			throw new ActivityIdNotFoundException("Activity id not found");
		}
			logger.info("The Activites of that Id has been deleted");
		EmployeeActivity employee=employeeActivity.get();
		employeeActivityRepository.delete(employee);

	}

}
