package com.demyx.user.exceptions;

public class UserReferenceException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	
	public UserReferenceException(String errorMessage) {
		super(errorMessage);
	}

}
