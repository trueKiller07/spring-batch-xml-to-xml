package com.trax.europeangateway.exceptions;

public class EdsRestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EdsRestException(Throwable cause) {
		super(cause);
	}

	public EdsRestException(String message) {
		super(message);
	}

	public EdsRestException(Throwable cause, String message) {
		super(message, cause);
	}

}
