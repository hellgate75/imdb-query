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
public class IMDBApiEngine implements APIEngine {

	Map<String, String> propertyMapping =  new ConcurrentHashMap<>(0);

	/**
	 * Default constructor
	 */
	public IMDBApiEngine() {
		super();
		propertyMapping.put(Constants.MOVIE_PROPERTY_RESULTS, "data,results,titles");
		propertyMapping.put(Constants.MOVIE_PROPERTY_ID, "id");
		propertyMapping.put(Constants.MOVIE_PROPERTY_PICTURE, "thumbnail");
		propertyMapping.put(Constants.MOVIE_PROPERTY_TITLE, "title");
		propertyMapping.put(Constants.MOVIE_PROPERTY_URL, "url");
		propertyMapping.put(Constants.MOVIE_PROPERTY_DETAIL_RESULTS, "data");
		propertyMapping.put(Constants.MOVIE_PROPERTY_DIRECTORS, "directors");
		propertyMapping.put(Constants.MOVIE_PROPERTY_YEAR, "released");
	}

	/* (non-Javadoc)
	 * @see com.web.libraries.imdbquery.api.types.APIEngine#getSelector()
	 */
	@Override
	public String getSelector() {
		return "imdb";
	}

	/* (non-Javadoc)
	 * @see com.web.libraries.imdbquery.api.types.APIEngine#getDescription()
	 */
	@Override
	public String getDescription() {
		return "IMDB Rest API implementation";
	}

	/* (non-Javadoc)
	 * @see com.web.libraries.imdbquery.api.types.APIEngine#getAPIKey()
	 */
	@Override
	public String getAPIKey() {
		return "8119e3fc-2ef8-409e-8ee1-1acbd15f3fda";
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
		return "http://imdb.wemakesites.net/api/search?q={searchText}&api_key={apiKey}";
	}

	/* (non-Javadoc)
	 * @see com.web.libraries.imdbquery.api.types.APIEngine#getDetailsRestURL()
	 */
	@Override
	public String getDetailsRestURL() {
		return "http://imdb.wemakesites.net/api/{id}?api_key={apiKey}";
	}

	/* (non-Javadoc)
	 * @see com.web.libraries.imdbquery.api.types.APIEngine#getFieldMapping()
	 */
	@Override
	public Map<String, String> getFieldMapping() {
		return propertyMapping;
	}

}
