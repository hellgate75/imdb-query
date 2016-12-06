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
public class TMDBApiEngine implements APIEngine {

	Map<String, String> propertyMapping =  new ConcurrentHashMap<>(0);

	/**
	 * Default constructor
	 */
	public TMDBApiEngine() {
		super();
		propertyMapping.put(Constants.MOVIE_PROPERTY_RESULTS, "results");
		propertyMapping.put(Constants.MOVIE_PROPERTY_ID, "id");
		propertyMapping.put(Constants.MOVIE_PROPERTY_PICTURE, "poster_path");
		propertyMapping.put(Constants.MOVIE_PROPERTY_TITLE, "title");
		/* No site URL provided */
//		propertyMapping.put(Constants.MOVIE_PROPERTY_URL, "url");
		/* No sub details*/
//		propertyMapping.put(Constants.MOVIE_PROPERTY_DETAIL_RESULTS, "data");
		/* No director provided */
//		propertyMapping.put(Constants.MOVIE_PROPERTY_DIRECTOR, "director");
		propertyMapping.put(Constants.MOVIE_PROPERTY_YEAR, "release_date");
	}

	/* (non-Javadoc)
	 * @see com.web.libraries.imdbquery.api.types.APIEngine#getSelector()
	 */
	@Override
	public String getSelector() {
		return "tmdb";
	}

	/* (non-Javadoc)
	 * @see com.web.libraries.imdbquery.api.types.APIEngine#getDescription()
	 */
	@Override
	public String getDescription() {
		return "The Movie DB Rest API implementation (use -DexactSearch=0)";
	}

	/* (non-Javadoc)
	 * @see com.web.libraries.imdbquery.api.types.APIEngine#getAPIKey()
	 */
	@Override
	public String getAPIKey() {
		return "b2ea7a4cc24cfc11d27f0c514d5fffee";
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
		/*NOTE : Here for the time gap we have not handle the multi-page with reference of attributes : 'total_pages' and 'pages', and URI query attribute (counter) 'page' to seek the next one ... */
		return "https://api.themoviedb.org/3/search/movie?query={searchText}&api_key={apiKey}";
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
