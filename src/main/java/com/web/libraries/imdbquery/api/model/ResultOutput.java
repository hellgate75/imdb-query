/**
 * 
 */
package com.web.libraries.imdbquery.api.model;

import java.util.Locale;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * JSON Object Result Object.
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 *
 */
public class ResultOutput extends JSONObject {

	/* (non-Javadoc)
	 */
	public ResultOutput() {
		super();
	}

	/* (non-Javadoc)
	 */
	public ResultOutput(JSONObject jo, String[] names) {
		super(jo, names);
	}

	/* (non-Javadoc)
	 */
	public ResultOutput(JSONTokener x) throws JSONException {
		super(x);
	}

	/* (non-Javadoc)
	 */
	@SuppressWarnings("rawtypes")
	public ResultOutput(Map map) {
		super(map);
	}

	/* (non-Javadoc)
	 */
	public ResultOutput(Object object, String[] names) {
		super(object, names);
	}

	/* (non-Javadoc)
	 */
	public ResultOutput(Object bean) {
		super(bean);
	}

	/* (non-Javadoc)
	 */
	public ResultOutput(String baseName, Locale locale) throws JSONException {
		super(baseName, locale);
	}

	/* (non-Javadoc)
	 */
	public ResultOutput(String source) throws JSONException {
		super(source);
	}


}
