package com.web.libraries.imdbquery.api.types;

import com.web.libraries.imdbquery.api.exception.WebRestQueryException;
import com.web.libraries.imdbquery.api.model.Result;
import com.web.libraries.imdbquery.api.model.movie.Movie;

/**
 * Interface that realize crawl results report in the specifc format
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 * @since Java 1.8
 * @see Result
 * @see WebRestQueryException
 */
public interface ResultFormatter extends CommandParameter {
	/**
	 * Provides the Movie format feature
	 * @param content Result Web Page to be exported in report 
	 * @return (Object) The formatted Movie
	 * @throws WebRestQueryException Exception occurred during the Site format
	 */
	Object format(Movie content) throws WebRestQueryException;


}
