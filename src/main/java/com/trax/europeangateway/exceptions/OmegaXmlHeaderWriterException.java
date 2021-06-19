package com.trax.europeangateway.exceptions;

public class OmegaXmlHeaderWriterException extends RuntimeException {
	
	public OmegaXmlHeaderWriterException(Throwable cause) {
		super(cause);
	}

	public OmegaXmlHeaderWriterException(String message) {
		super(message);
	}

	public OmegaXmlHeaderWriterException(Throwable cause, String message) {
		super(message, cause);
	}
}
