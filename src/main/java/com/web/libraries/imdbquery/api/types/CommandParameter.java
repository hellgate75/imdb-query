package com.web.libraries.imdbquery.api.types;

import com.web.libraries.imdbquery.api.exception.WebRestQueryException;
import com.web.libraries.imdbquery.api.model.Result;

/**
 * Interface that realize a base command parameter functions. Any implementing parameter realize a specific Web Crawler feature.
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 * @since Java 1.8
 * @see Result
 * @see ResultFormatter
 * @see WebRestQueryException
 */
public interface CommandParameter {
	/**
	 * Retrieve the parameter selector name
	 * @return (String) The parameter selector name 
	 */
	String getSelector();
	
	/**
	 * Retrieve the parameter description
	 * @return (String) The parameter description 
	 */
	String getDescription();

	/**
	 * Retrieve the active state of the parameter object
	 * @return (Boolean) The parameter active state flag
	 */
	Boolean isActive();

}
