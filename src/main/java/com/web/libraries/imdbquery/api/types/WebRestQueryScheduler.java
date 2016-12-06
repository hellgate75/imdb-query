package com.web.libraries.imdbquery.api.types;

import com.web.libraries.imdbquery.api.model.Result;
import com.web.libraries.imdbquery.api.model.ResultOutput;

/**
 * Interface that realize crawl scheduling feature for a Detailed Movie information
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 * @since Java 1.8
 * @see Result
 */
public interface WebRestQueryScheduler {
	/**
	 * Require further information for the Detailed Movie
	 * @param detailedMovie  Detailed Movie scheduled for require further information
	 */
	void schedule(ResultOutput detailedMovie);
}
