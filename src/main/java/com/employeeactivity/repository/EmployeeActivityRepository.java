package com.employeeactivity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.employeeactivity.entity.EmployeeActivity;

public interface EmployeeActivityRepository extends JpaRepository<EmployeeActivity, Long>{

	Page<EmployeeActivity> findByEmployeeCode(Long employeeCode, Pageable pageable);

	

}
