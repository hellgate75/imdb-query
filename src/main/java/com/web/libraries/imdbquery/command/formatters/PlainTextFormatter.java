package com.web.libraries.imdbquery.command.formatters;

import com.web.libraries.imdbquery.api.exception.WebRestQueryException;
import com.web.libraries.imdbquery.api.model.movie.Movie;
import com.web.libraries.imdbquery.api.types.ResultFormatter;
import com.web.libraries.imdbquery.api.utils.Logger;

/**
 * Formtter that formats the Detailed Movie Information for the export
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 * @since Java 1.8
 * @see ResultFormatter
 */
public class PlainTextFormatter implements ResultFormatter {

	/* (non-Javadoc)
	 * @see com.web.libraries.imdbquery.api.types.ResultFormatter#format(com.web.libraries.imdbquery.api.model.Result)
	 */
	@Override
	public Object format(Movie content) throws WebRestQueryException {
		try {
			if(content!=null) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("<");
				buffer.append(content.getTitle());
				buffer.append(">\t\t<");
				buffer.append(content.getYear()>0 ? "" + content.getYear() : "    ");
				buffer.append(">\t\t<");
				buffer.append(content.getDirector());
				buffer.append(">");
				return buffer.toString();
			}
			return "";
		} catch (Exception exception) {
			Logger.error("Exception during the format of a site ...", exception, PlainTextFormatter.class);
			throw new  WebRestQueryException("Exception during the format of a site ...", exception);
		}
	}

	/* (non-Javadoc)
	 * @see com.web.libraries.imdbquery.api.types.CommandParameter#getSelector()
	 */
	@Override
	public String getSelector() {
		return "plain";
	}

	/* (non-Javadoc)
	 * @see com.web.libraries.imdbquery.api.types.CommandParameter#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Plain Text Site Map Format";
	}

	/* (non-Javadoc)
	 * @see com.web.libraries.imdbquery.api.types.CommandParameter#isActive()
	 */
	@Override
	public Boolean isActive() {
		return Boolean.TRUE;
	}

}
