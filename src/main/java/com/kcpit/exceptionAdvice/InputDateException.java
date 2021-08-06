package com.kcpit.exceptionAdvice;

public class InputDateException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InputDateException() {
		super();
	}

	public InputDateException(String ex) {
		super(ex);
	}
}
