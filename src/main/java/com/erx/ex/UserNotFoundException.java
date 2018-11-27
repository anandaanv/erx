package com.erx.ex;

public class UserNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;

	public UserNotFoundException(String userId, Throwable cause) {
		super(cause);
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserNotFoundException [userId=" + userId + "]";
	}
	
	
}
