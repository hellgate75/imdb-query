package com.web.libraries.imdbquery.api.types;

import java.util.Map;

/**
 * Interface that realize a base API Engine features. Database information used for loading data from the IMDb site.
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 * @since Java 1.8
 */
public interface APIEngine {
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

	/**
	 * Retrieve the REST parametrized URL to use to retrieve the movie list
	 * @return (String) REST parametrized URL to use to retrieve the movie list
	 */
	String getQueryRestURL();

	/**
	 * Retrieve the REST Service API key to use to retrieve the movie list
	 * @return (String) REST Service API key to use to retrieve the movie list
	 */
	String getAPIKey();

	/**
	 * Retrieve the REST parametrized URL to use to retrieve the movie detailed information
	 * @return (String) REST parametrized URL to use to retrieve the movie detailed information
	 */
	String getDetailsRestURL();

	/**
	 * Retrieve the field mapping association
	 * @return (Map) field mapping association
	 */
	Map<String, String> getFieldMapping();
	
}
