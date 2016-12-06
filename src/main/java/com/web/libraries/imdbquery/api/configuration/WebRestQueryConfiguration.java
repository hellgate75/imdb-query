package com.web.libraries.imdbquery.api.configuration;

import com.web.libraries.imdbquery.api.WebRestQuery;
import com.web.libraries.imdbquery.api.types.APIEngine;

/**
 * Web Crawler Congifuration 
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 * @since Java 1.8
 * @see WebRestQuery
 * @see WebRestQueryConfigurationBuilder
 */
public class WebRestQueryConfiguration {

	private APIEngine apiDatabase;

	private String movieTitle;
	
	private String formatter;
	
	private String outputType;
	
	private Integer thredExtends;
	
	private Boolean exactSearch;

	/**
	 * Default Constructor
	 * @param apiDatabase The current api database used to surf within
	 * @param movieTitle The current movie title used to search for
	 * @param formatter The current site map output formatter
	 * @param outputType The current output device to export the site map report
	 * @param thredExtends The number of threads used to extend the reading of the Web Site surfing queue
	 */
	protected WebRestQueryConfiguration(APIEngine apiDatabase, String movieTitle, String formatter, String outputType, int thredExtends, boolean exactSearch) {
		super();
		this.apiDatabase = apiDatabase;
		this.movieTitle = movieTitle;
		this.formatter = formatter;
		this.outputType = outputType;
		this.thredExtends = thredExtends;
		this.exactSearch = exactSearch;
	}

	/**
	 * Retrieves the current API Database to use
	 * @return (APIEngine) The API Database to use
	 * @see APIEngine
	 */
	public APIEngine getAPIDatabase() {
		return apiDatabase;
	}

	/**
	 * Retrieves the current Movie title to search for
	 * @return (String) The Movie title to search for
	 */
	public String getMovieTitle() {
		return movieTitle;
	}

	/**
	 * Retrieves the current formatter used to parse the result
	 * @return (String) The formatter
	 */
	public String getFormatter() {
		return formatter;
	}

	/**
	 * Retrieves the current output device to export the result report
	 * @return (String) The Output Type
	 */
	public String getOutputType() {
		return outputType;
	}

	/**
	 * Retrieves the current thread extends number
	 * @return The number of allowed Thred Extends
	 */
	public Integer getThredExtends() {
		return thredExtends;
	}

	/**
	 * Retrieves exact search criteria flag
	 * @return The exact search criteria flag
	 */
	public Boolean getExactSearch() {
		return exactSearch;
	}
	

}
