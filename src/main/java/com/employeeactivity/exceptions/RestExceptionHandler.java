package com.employeeactivity.exceptions;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;





@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value=EmployeeActivitiesNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleCustomerNotFoundException(EmployeeActivitiesNotFoundException employeeActivitiesNotFoundException){
		ErrorMessage errorMessage=new ErrorMessage();
		errorMessage.setErrorCode("Employee Activities");
		errorMessage.setErrorMessage(employeeActivitiesNotFoundException.getMessage());
		
		return new ResponseEntity<ErrorMessage>(errorMessage,HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(value=ActivityIdNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleCustomerNotFoundException(ActivityIdNotFoundException activityIdNotFoundException){
		ErrorMessage errorMessage=new ErrorMessage();
		errorMessage.setErrorCode("Activity Id");
		errorMessage.setErrorMessage(activityIdNotFoundException.getMessage());
		
		return new ResponseEntity<ErrorMessage>(errorMessage,HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorMessage> handle(ConstraintViolationException constraintViolationException) {
	ErrorMessage errorResponse = new ErrorMessage();
	errorResponse.setErrorCode("125");
	Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
	String errorMessage = "";
	if (!violations.isEmpty()) {
	StringBuilder builder = new StringBuilder();
	violations.forEach(violation -> builder.append(" ," + violation.getMessage()));
	errorMessage = builder.toString();
	} else {
	errorMessage = "ConstraintViolationException occured.";
	}
	errorResponse.setErrorMessage(errorMessage.substring(2));
	return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException  argInvalidException, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorMessage response = new ErrorMessage();
		response.setErrorCode("Error Response");
		String allFieldErrors = argInvalidException.getBindingResult().getFieldErrors().stream()
				.map(e -> e.getDefaultMessage()).collect(Collectors.joining(", "));
		response.setErrorMessage(allFieldErrors);
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}

}
