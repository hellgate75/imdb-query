package com.web.libraries.imdbquery;

import com.web.libraries.imdbquery.api.WebRestQuery;
import com.web.libraries.imdbquery.api.WebRestQueryHelper;
import com.web.libraries.imdbquery.api.exception.WebRestQueryException;

/**
 * Web Crawler Main class that allow to execute the Crawler by command line or behalf the invocation
 * of the main method. 
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 * @since Java 1.8
 * @see WebRestQuery
 * @see WebRestQueryException
 */
public class Main {
	static {
		System.setProperty("log4j.configurationFile", "log4j2.xml");
	}
	
	/**
	 * Main method
	 * @param arguments input arguments list
	 * @throws WebRestQueryException Any exception raised during the Web Crawler boostrap
	 */
	public static void main(String[] arguments) throws WebRestQueryException, Exception {
		WebRestQueryHelper.parseParametersAndRunCrawler(arguments);
	}

}
