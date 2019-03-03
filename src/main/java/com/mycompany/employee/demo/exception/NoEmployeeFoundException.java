package com.mycompany.employee.demo.exception;

public class NoEmployeeFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public NoEmployeeFoundException(String message) {
		super(message);
	}
}
