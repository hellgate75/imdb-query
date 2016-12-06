
package com.web.libraries.imdbquery.command.collectors;

import com.web.libraries.imdbquery.api.exception.WebRestQueryException;
import com.web.libraries.imdbquery.api.model.Result;
import com.web.libraries.imdbquery.api.model.movie.Movie;
import com.web.libraries.imdbquery.api.types.ResultCollector;
import com.web.libraries.imdbquery.api.types.ResultFormatter;
import com.web.libraries.imdbquery.api.utils.Logger;

/**
 * Collector that prints the formatted Detailed Movie Information result on the standard output
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 * @since Java 1.8
 * @see ResultCollector
 */
public class StandarOutputResultCollector implements ResultCollector {
	
	private ResultFormatter formatter;

	/* (non-Javadoc)
	 * @see com.web.libraries.imdbquery.api.types.ResultCollector#exportPage(com.web.libraries.imdbquery.api.model.Result)
	 */
	@Override
	public void exportPage(Result content) throws WebRestQueryException {
		try {
			for(Movie child: content.getMovieList()) {
				System.out.println(formatter.format(child));
			}
		} catch (final Exception exception) {
			Logger.error("Exception during the export of a site ...", exception, StandarOutputResultCollector.class);
			throw new  WebRestQueryException("Exception during the export of a site ...", exception);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.web.libraries.imdbquery.api.types.ResultCollector#setFormatter(com.web.libraries.imdbquery.api.types.ResultFormatter)
	 */
	@Override
	public void setFormatter(ResultFormatter formatter) {
		this.formatter = formatter;
	}

	/* (non-Javadoc)
	 * @see com.web.libraries.imdbquery.api.types.CommandParameter#getSelector()
	 */
	@Override
	public String getSelector() {
		return "stdout";
	}

	/* (non-Javadoc)
	 * @see com.web.libraries.imdbquery.api.types.CommandParameter#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Standard Output Writer";
	}

	/* (non-Javadoc)
	 * @see com.web.libraries.imdbquery.api.types.CommandParameter#isActive()
	 */
	@Override
	public Boolean isActive() {
		return Boolean.TRUE;
	}
	

}
