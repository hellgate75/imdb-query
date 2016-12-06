/**
 * 
 */
package com.web.libraries.imdbquery.api.controller.engines;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.web.libraries.imdbquery.api.types.APIEngine;
import com.web.libraries.imdbquery.api.utils.Constants;

/**
 * Interface that realize a base API Engine Factory Controller.
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 * @since Java 1.8
 */
public class OMDBApiEngine implements APIEngine {

	Map<String, String> propertyMapping =  new ConcurrentHashMap<>(0);

	/**
	 * Default constructor
	 */
	public OMDBApiEngine() {
		super();
		propertyMapping.put(Constants.MOVIE_PROPERTY_RESULTS, "Search");
		propertyMapping.put(Constants.MOVIE_PROPERTY_ID, "imdbID");
		propertyMapping.put(Constants.MOVIE_PROPERTY_PICTURE, "Poster");
		propertyMapping.put(Constants.MOVIE_PROPERTY_TITLE, "Title");
		/* No site URL provided */
//		propertyMapping.put(Constants.MOVIE_PROPERTY_URL, "url");
		/* No sub details*/
//		propertyMapping.put(Constants.MOVIE_PROPERTY_DETAIL_RESULTS, "data");
		/* No director provided */
//		propertyMapping.put(Constants.MOVIE_PROPERTY_DIRECTOR, "director");
		propertyMapping.put(Constants.MOVIE_PROPERTY_YEAR, "Year");
	}

	/* (non-Javadoc)
	 * @see com.web.libraries.imdbquery.api.types.APIEngine#getSelector()
	 */
	@Override
	public String getSelector() {
		return "omdb";
	}

	/* (non-Javadoc)
	 * @see com.web.libraries.imdbquery.api.types.APIEngine#getDescription()
	 */
	@Override
	public String getDescription() {
		return "OMDB Rest API implementation - APIKey activation in progress";
	}

	/* (non-Javadoc)
	 * @see com.web.libraries.imdbquery.api.types.APIEngine#getAPIKey()
	 */
	@Override
	public String getAPIKey() {
		return "7b99b84f";
	}

	/* (non-Javadoc)
	 * @see com.web.libraries.imdbquery.api.types.APIEngine#isActive()
	 */
	@Override
	public Boolean isActive() {
		return Boolean.TRUE;
	}

	/* (non-Javadoc)
	 * @see com.web.libraries.imdbquery.api.types.APIEngine#getQueryRestURL()
	 */
	@Override
	public String getQueryRestURL() {
		return "http://www.omdbapi.com/?s={searchText}&apikey={apiKey}&y=&plot=full&r=json";
	}

	/* (non-Javadoc)
	 * @see com.web.libraries.imdbquery.api.types.APIEngine#getDetailsRestURL()
	 */
	@Override
	public String getDetailsRestURL() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.web.libraries.imdbquery.api.types.APIEngine#getFieldMapping()
	 */
	@Override
	public Map<String, String> getFieldMapping() {
		return propertyMapping;
	}

}
