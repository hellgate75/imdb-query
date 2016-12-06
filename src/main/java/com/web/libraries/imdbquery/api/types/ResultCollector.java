package com.web.libraries.imdbquery.api.types;

import com.web.libraries.imdbquery.api.exception.WebRestQueryException;
import com.web.libraries.imdbquery.api.model.Result;

/**
 * Interface that realize crawl results report in the specifc format
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 * @since Java 1.8
 * @see Result
 * @see ResultFormatter
 * @see WebRestQueryException
 */
public interface ResultCollector extends CommandParameter {
	/**
	 * Export a Web Site Page in a report format
	 * @param content Result Web Page to be exported in report 
	 * @throws WebRestQueryException Exception occurred during the Site Export
	 */
	void exportPage(Result content) throws WebRestQueryException;

	/**
	 * Set the Site Page formatter
	 * @param formatter Result Formatter used to Format the Web Page output 
	 */
	void setFormatter(ResultFormatter formatter);

}
