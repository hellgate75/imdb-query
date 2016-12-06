package com.web.libraries.imdbquery.api.utils;

import com.web.libraries.imdbquery.api.types.ResultCollector;
import com.web.libraries.imdbquery.api.types.ResultFormatter;

/**
 * Constant Names Class
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 * @since Java 1.8
 */
public class Constants {

	/*
	 * WEB CRAWLER DEFAULT CONFIGURATIONS
	 */
	
	/**
	 * Identify a system property for the default result report formatter
	 * @see ResultFormatter
	 */
	public static final String PROPERTIES_DEFAULT_REPORT_FORMATTER = "com.web.libraries.imdbquery.defaultFormatter";
	/**
	 * Identify a system property for the default result report collector type
	 * @see ResultCollector
	 */
	public static final String PROPERTIES_DEFAULT_REPORT_COLLECTOR = "com.web.libraries.imdbquery.defaultCollector";
	/**
	 * Identify a system property for the default timeout in milliseconds used to delay calls and prevent the crawler is identified
	 * by the security systems
	 */
	public static final String PROPERTIES_DEFAULT_CRAWLER_ANTI_IDENTIFY_TIMEOUT = "com.web.libraries.imdbquery.crowlerIdentifyTimeout";
	/**
	 * Identify a system property for the number of parallel threads extended to surf the web site and descend the children
	 */
	public static final String PROPERTIES_DEFAULT_PARALLEL_THREADS = "com.web.libraries.imdbquery.parallelThreadsExtend";
	/**
	 * Identify a system property for the maximum surf descendant of the web site or zero in case of no limit
	 */
	public static final String PROPERTIES_DEFAULT_MAX_SURFING_LEVEL = "com.web.libraries.imdbquery.maxSurfLevel";
	/**
	 * Identify a system property for the default API Database
	 */
	public static final String PROPERTIES_DEFAULT_API_DATABASE = "com.web.libraries.imdbquery.apiDb";
	/**
	 * Identify a system property for the default API Database
	 */
	public static final String PROPERTIES_DEFAULT_EXACT_SEARCH = "com.web.libraries.imdbquery.exactSearch";
	/**
	 * Identify a system property for the default Query String
	 */
	public static final String PROPERTIES_DEFAULT_MOVIE_TITLE = "com.web.libraries.imdbquery.movieTitle";

	/*
	 * WEB CRAWLER COMMAND LINE PROPERTIES
	 */

	/**
	 * Identify a command line parsed property for the default result report formatter
	 * @see ResultFormatter
	 */
	public static final String COMMAND_PROPERTY_REPORT_FORMATTER = "format";
	/**
	 * Identify a command line parsed property for the default result report collector type
	 * @see ResultCollector
	 */
	public static final String COMMAND_PROPERTY_REPORT_COLLETOR = "output";
	/**
	 * Identify a command line parsed property for the number of parallel threads extended to surf the web site and descend the children
	 */
	public static final String COMMAND_PROPERTY_PARALLEL_THREADS = "extends";
	/**
	 * Identify a command line parsed property for the maximum surf descendant of the web site or zero in case of no limit
	 */
	public static final String COMMAND_PROPERTY_API_DB = "api";
	/**
	 * Identify a command line parsed property for movie title
	 */
	public static final String COMMAND_PROPERTY_MOVIE_TITLE = "movie";
	/**
	 * Identify a command line parsed property for movie title
	 */
	public static final String COMMAND_PROPERTY_EXACT_SEARCH = "exactSearch";
	/**
	 * Identify a command line parsed property printing the help
	 */
	public static final String COMMAND_PROPERTY_HELP_REQUEST = "help";

	/*
	 * Movie configuration
	 * */
	/**
	 * Identify a movie property reflecting the movie web reference  unique id
	 */
	public static final String MOVIE_PROPERTY_ID = "uid";

	/**
	 * Identify a movie property reflecting the movie title
	 */
	public static final String MOVIE_PROPERTY_TITLE = "title";
	/**
	 * Identify a movie property reflecting the movie director
	 */
	public static final String MOVIE_PROPERTY_DIRECTOR = "director";
	/**
	 * Identify a movie property reflecting the movie director
	 */
	public static final String MOVIE_PROPERTY_DIRECTORS = "directors";
	/**
	 * Identify a movie property reflecting the movie year
	 */
	public static final String MOVIE_PROPERTY_YEAR = "year";
	/**
	 * Identify a movie property reflecting the movie picture
	 */
	public static final String MOVIE_PROPERTY_PICTURE = "picture";
	/**
	 * Identify a movie property reflecting the movie URL
	 */
	public static final String MOVIE_PROPERTY_URL = "url";
	/**
	 * Identify a movie property reflecting the movie site provider
	 */
	public static final String MOVIE_PROPERTY_PROVIDER = "provider";
	/**
	 * Identify a movie property reflecting the movie search results field
	 */
	public static final String MOVIE_PROPERTY_RESULTS = "results";
	/**
	 * Identify a movie property reflecting the movie details search results field
	 */
	public static final String MOVIE_PROPERTY_DETAIL_RESULTS = "dResults";
}
