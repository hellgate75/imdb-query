package com.web.libraries.imdbquery.api.configuration;

import com.web.libraries.imdbquery.api.WebRestQuery;
import com.web.libraries.imdbquery.api.WebRestQueryHelper;
import com.web.libraries.imdbquery.api.types.APIEngine;

/**
 * Web Crawler Configuration Builder 
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 * @since Java 1.8
 * @see WebRestQuery
 * @see WebRestQueryConfiguration
 */
public class WebRestQueryConfigurationBuilder {
	
	
	private String formatter = WebRestQueryHelper.getDefultFormatter();
	
	private String outputType = WebRestQueryHelper.getDefultOutputType();
	
	private Integer thredExtends = WebRestQueryHelper.getDefultThredExtends();

	private String movieTitle = WebRestQueryHelper.getDefultMovieTitle();

	private APIEngine apiDatabase = WebRestQueryHelper.getDefultApiDatabase();

	private Boolean exactSearch = WebRestQueryHelper.getDefultExactSearch();

	/**
	 * Default Constructor
	 */
	public WebRestQueryConfigurationBuilder() {
		super();
	}
	
	/**
	 * Define the API Database (Default : {@link WebRestQueryHelper#getDefultApiDatabase()})
	 * @param apiDb API Database to use
	 * @return the builder instance
	 */
	public WebRestQueryConfigurationBuilder apiDatabase(APIEngine apiDb) {
		this.apiDatabase = apiDb;
		return this;
	}

	/**
	 * Define the Movie Title as Query Parameter (Default : {@link WebRestQueryHelper#getDefultMovieTitle()})
	 * @param title Web REST Call search attribute
	 * @return the builder instance
	 */
	public WebRestQueryConfigurationBuilder movieTitle(String title) {
		this.movieTitle = title;
		return this;
	}
	
	/**
	 * Define the Web Site Map Format type (Default : {@link WebRestQueryHelper#getDefultFormatter()})
	 * @param formatter The formatter selector
	 * @return the builder instance
	 */
	public WebRestQueryConfigurationBuilder formatter(String formatter) {
		this.formatter = formatter;
		return this;
	}
	
	/**
	 * Define the Web Site Map Output device type (Default : {@link WebRestQueryHelper#getDefultOutputType()})
	 * @param outputType The output type selector
	 * @return the builder instance
	 */
	public WebRestQueryConfigurationBuilder outputType(String outputType) {
		this.outputType = outputType;
		return this;
	}

	/**
	 * Define the Web exact search for the title criteria (Default : {@link WebRestQueryHelper#getDefultExactSearch()})
	 * @param exactSearch The exact search criteria flag
	 * @return the builder instance
	 */
	public WebRestQueryConfigurationBuilder exactSearch(Boolean exactSearch) {
		this.exactSearch = exactSearch;
		return this;
	}

	
	/**
	 * Define the Web Site surfing maximum thread extends (Default : {@link WebRestQueryHelper#getDefultThredExtends()})
	 * @param thredExtends Minimum operative threads during the site surfing operations
	 * @return the builder instance
	 */
	public WebRestQueryConfigurationBuilder thredExtends(Integer thredExtends) {
		this.thredExtends = thredExtends;
		return this;
	}
	
	
	
	/**
	 * Build and return the {@link WebRestQueryConfiguration}}
	 * @return The Web Crawler Configuration
	 */
	public WebRestQueryConfiguration build() {
		return new WebRestQueryConfiguration(apiDatabase, movieTitle, formatter, outputType, thredExtends, exactSearch);
	}

}
