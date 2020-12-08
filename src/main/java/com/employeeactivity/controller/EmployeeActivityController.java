package com.employeeactivity.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employeeactivity.dto.EmployeeActivityDto;
import com.employeeactivity.dto.EmployeeActivityResponseDto;
import com.employeeactivity.dto.UpdateEmployeeActivityDto;
import com.employeeactivity.exceptions.ActivityIdNotFoundException;
import com.employeeactivity.service.AddEmployeeActivityService;
import com.employeeactivity.service.FetchEmployeeActivityService;
import com.employeeactivity.service.RemoveEmployeeActivityService;
import com.employeeactivity.service.UpdateEmployeeActivityService;
@Validated
@RestController
public class EmployeeActivityController {
	
	@Autowired
	AddEmployeeActivityService addEmployeeActivityService;
	
	@Autowired
	FetchEmployeeActivityService fetchEmployeeActivityService;
	
	@Autowired
	RemoveEmployeeActivityService removeEmployeeActivityService;
	
	@Autowired
	UpdateEmployeeActivityService updateEmployeeActivityService;
	
	
	@PostMapping("/addEmployeeActivity")
	public ResponseEntity<String> addEmployeeActivity(@Valid @RequestBody List<@Valid EmployeeActivityDto> employeeActivityDto){
		addEmployeeActivityService.addEmployeeActivity(employeeActivityDto);
		return new ResponseEntity<String>("Employee Activity Added Successfully",HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/getEmployeeActivities")
	public ResponseEntity<List<EmployeeActivityResponseDto>> getEmployeeActivity(@RequestParam Long employeeCode, @RequestParam int pageNumber, @RequestParam int pageSize) 
					throws Exception{
		List<EmployeeActivityResponseDto> employee=fetchEmployeeActivityService.getEmployeeActivity(employeeCode,pageNumber,pageSize);
		return new ResponseEntity<List<EmployeeActivityResponseDto>>(employee,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/removeEmployeeActivity")
	public ResponseEntity<String> removeEmployeeActivity(@RequestParam Long id) throws Exception{
		removeEmployeeActivityService.removeEmployeeActivity(id);
		return new ResponseEntity<String>("Employee Activity removed Successfully.",HttpStatus.OK);
		
	}
	
	@PatchMapping("/updateEmployeeActivity")
	public ResponseEntity<String> updateEmployeeActivity(@RequestBody UpdateEmployeeActivityDto updateEmployeeActivityDto) throws Exception{
		updateEmployeeActivityService.updateEmployeeActivity(updateEmployeeActivityDto);
		return new ResponseEntity<String>("Employee Activity updated Successfully",HttpStatus.ACCEPTED) ;
		
	}

}
