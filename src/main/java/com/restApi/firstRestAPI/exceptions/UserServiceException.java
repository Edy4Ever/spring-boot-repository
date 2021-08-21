package com.restApi.firstRestAPI.exceptions;

//This class will be used to pass it to the AppExceptionHandler class to used our own customed exceptions
public class UserServiceException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserServiceException(String message) {
		super(message);
	}
}
