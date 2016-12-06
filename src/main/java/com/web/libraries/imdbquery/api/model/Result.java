package com.web.libraries.imdbquery.api.model;

import java.util.ArrayList;
import java.util.List;

import com.web.libraries.imdbquery.api.exception.WebRestQueryException;
import com.web.libraries.imdbquery.api.model.movie.Movie;
import com.web.libraries.imdbquery.api.utils.Logger;
import com.web.libraries.imdbquery.api.utils.MovieMapper;

/**
 * Model class that realize the Visited page base information
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 * @since Java 1.8
 */
public class Result {
	private String searchTitle;
	private List<Movie> movies=new ArrayList<Movie>(0);
	private MovieMapper mapper;
	
	
	public Result(String searchTitle, MovieMapper mapper) {
		super();
		this.searchTitle = searchTitle;
		this.mapper = mapper;
	}


	/**
	 * Retrieve the title we are looking for ...
	 * @return the searchTitle
	 */
	public String getSearchTitle() {
		return searchTitle;
	}


	/**
	 * Retrieve result movie list
	 * @return (List) Movie List
	 */
	public List<Movie> getMovieList() {
		return movies;
	}

	/**
	 * Method who adds new Result Output to the result
	 * @param child Child response
	 * @throws WebRestQueryException Exception occurred during the result parsing
	 */
	public void addResultOutput(ResultOutput child) throws WebRestQueryException {
		try {
			movies.add(mapper.mapMovie(child));
		} catch (WebRestQueryException e) {
			Logger.error("Exception occurred during the result ouput mapping ...", e, Result.class);
			throw e;
		}
	}
	
}
