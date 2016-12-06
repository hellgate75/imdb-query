package com.web.libraries.imdbquery.api.utils;

import com.web.libraries.imdbquery.api.exception.WebRestQueryException;
import com.web.libraries.imdbquery.api.model.ResultOutput;
import com.web.libraries.imdbquery.api.model.movie.Movie;
import com.web.libraries.imdbquery.api.types.APIEngine;

/**
 * Movie object mapper
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 * @since Java 1.8
 */
public class MovieMapper {

	private APIEngine engine;
	
	/**
	 * Default constructor
	 * @param engine mapping reference engine
	 */
	public MovieMapper(APIEngine engine) {
		super();
		this.engine = engine;
	}
	
	/**
	 * Convert and recover the movie title from the raw data
	 * @param raw The input data
	 * @return the movie title
	 * @throws WebRestQueryException Generic Movie data parser exception
	 */
	public String getTitle(ResultOutput raw) throws WebRestQueryException {
		try {
			return raw.getString(engine.getFieldMapping().get(Constants.MOVIE_PROPERTY_TITLE));
		} catch (Throwable e) {
			throw new WebRestQueryException("Unable to parse the movie title for the raw data = " + raw, e);
		}
	}

	/**
	 * Convert and recover the movie provider uid from the raw data
	 * @param raw The input data
	 * @return the provider uid
	 * @throws WebRestQueryException Generic Movie data parser exception
	 */
	public String getUID(ResultOutput raw) throws WebRestQueryException {
		try {
			return raw.getString(Constants.MOVIE_PROPERTY_ID);
		} catch (Throwable e) {
			throw new WebRestQueryException("Unable to parse the movie provider uid for the raw data = " + raw, e);
		}
	}

	/**
	 * Convert and recover the movie director from the raw data
	 * @param raw The input data
	 * @return the movie director
	 * @throws WebRestQueryException Generic Movie data parser exception
	 */
	public String getDirector(ResultOutput raw) throws WebRestQueryException {
		try {
			return raw.getString(Constants.MOVIE_PROPERTY_DIRECTOR);
		} catch (Throwable e) {
			throw new WebRestQueryException("Unable to parse the movie director for the raw data = " + raw, e);
		}
	}

	/**
	 * Convert and recover the movie year from the raw data
	 * @param raw The input data
	 * @return the movie year
	 * @throws WebRestQueryException Generic Movie data parser exception
	 */
	public Integer getYear(ResultOutput raw) throws WebRestQueryException {
		try {
			return raw.getInt(Constants.MOVIE_PROPERTY_YEAR);
		} catch (Throwable e) {
			throw new WebRestQueryException("Unable to parse the movie year for the raw data = " + raw, e);
		}
	}
	
	/**
	 * Convert and recover the movie picture URL from the raw data
	 * @param raw The input data
	 * @return the movie picture URL
	 * @throws WebRestQueryException Generic Movie data parser exception
	 */
	public String getPictureURL(ResultOutput raw) throws WebRestQueryException {
		try {
			return raw.getString(Constants.MOVIE_PROPERTY_PICTURE);
		} catch (Throwable e) {
			throw new WebRestQueryException("Unable to parse the movie picture URL for the raw data = " + raw, e);
		}
	}
	
	/**
	 * Convert and recover the movie provider site URL from the raw data
	 * @param raw The input data
	 * @return the movie provider site URL
	 * @throws WebRestQueryException Generic Movie data parser exception
	 */
	public String getMovieURL(ResultOutput raw) throws WebRestQueryException {
		try {
			return raw.getString(Constants.MOVIE_PROPERTY_URL);
		} catch (Throwable e) {
			throw new WebRestQueryException("Unable to parse the movie provider site URL for the raw data = " + raw, e);
		}
	}
	
	/**
	 * Recover the movie engine provider selector name
	 * @return the movie engine provider selector name
	 */
	public String getMovieProvider() {
		return engine.getSelector();
	}
	
	/**
	 * Converts a Movie form a raw result object
	 * @param output the result output
	 * @return the parsed movie or null
	 * @throws WebRestQueryException Generic Movie data parser exception
	 */
	public synchronized Movie mapMovie(ResultOutput output) throws WebRestQueryException {
		if (output!=null) {
			return new Movie(this.getUID(output), this.getTitle(output), this.getDirector(output), this.getYear(output), this.getPictureURL(output), this.getMovieURL(output), this.getMovieProvider());
		}
		return null;
	}

	
}
