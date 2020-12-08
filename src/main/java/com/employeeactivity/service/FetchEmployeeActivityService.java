package com.employeeactivity.service;

import java.util.List;

import com.employeeactivity.dto.EmployeeActivityResponseDto;

public interface FetchEmployeeActivityService {

	List<EmployeeActivityResponseDto> getEmployeeActivity(Long employeeCode, int pageNumber, int pageSize) throws Exception;

}
