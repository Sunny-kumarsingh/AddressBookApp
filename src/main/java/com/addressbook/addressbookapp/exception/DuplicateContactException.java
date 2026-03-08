package com.addressbook.addressbookapp.exception;

public class DuplicateContactException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DuplicateContactException(String msg) {
		super(msg);
	}
}