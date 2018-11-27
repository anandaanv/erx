package com.erx.ex;

public class ERxException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ERxException(String message, Throwable cause) {
		super(message, cause);
	}

	public ERxException(String message) {
		super(message);
	}

	public ERxException(Throwable cause) {
		super(cause);
	}

}
