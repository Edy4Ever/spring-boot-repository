package com.restApi.firstRestAPI.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.restApi.firstRestAPI.ui.model.response.ErrorMessage;

//This class was created to listen to all the exceptions GetMapping, PostMapping
@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value= { Exception.class })
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request){
		String errorMessageDescription = ex.getLocalizedMessage();
		
		if(errorMessageDescription == null ) errorMessageDescription = ex.toString();
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getLocalizedMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//Personalized Exception
/*	@ExceptionHandler(value= { NullPointerException.class })
	public ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request){
		String errorMessageDescription = ex.getLocalizedMessage();
		
		if(errorMessageDescription == null ) errorMessageDescription = ex.toString();
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getLocalizedMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(value= { UserServiceException.class })
	public ResponseEntity<Object> handleUserServiceException(UserServiceException ex, WebRequest request){
		String errorMessageDescription = ex.getLocalizedMessage();
		
		if(errorMessageDescription == null ) errorMessageDescription = ex.toString();
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getLocalizedMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}*/
	
	//How to combine to handle 2 or more Exceptions
	@ExceptionHandler(value= { NullPointerException.class, UserServiceException.class })
	public ResponseEntity<Object> handleSpecificExceptions(Exception ex, WebRequest request){
		String errorMessageDescription = ex.getLocalizedMessage();
		
		if(errorMessageDescription == null ) errorMessageDescription = ex.toString();
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getLocalizedMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
