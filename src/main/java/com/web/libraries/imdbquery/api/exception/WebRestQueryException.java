package com.web.libraries.imdbquery.api.exception;

import com.web.libraries.imdbquery.api.model.Result;

/**
 * Exception raised during most of the crawler operation bounding cuase ones or defying
 * custom exception cases
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 * @since Java 1.8
 * @see Result
 */
public class WebRestQueryException extends Exception {

	/**
	 * Class Serialization Version ID
	 */
	private static final long serialVersionUID = -7213289577602014399L;

	/**
	 * Create an exception by message and root exception cause
	 * @param message Message related to the exception
	 * @param cause Exception that has generated this one
	 */
	public WebRestQueryException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Create an exception by message
	 * @param message Message related to the exception
	 */
	public WebRestQueryException(String message) {
		super(message);
	}

	/**
	 * Create an exception by root exception cause
	 * @param cause Exception that has generated this one
	 */
	public WebRestQueryException(Throwable cause) {
		super(cause);
	}
	
}
