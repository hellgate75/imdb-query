package com.web.libraries.imdbquery.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import com.web.libraries.imdbquery.api.configuration.WebRestQueryConfiguration;
import com.web.libraries.imdbquery.api.exception.WebRestQueryException;
import com.web.libraries.imdbquery.api.model.Result;
import com.web.libraries.imdbquery.api.model.ResultOutput;
import com.web.libraries.imdbquery.api.types.WebRestQueryScheduler;
import com.web.libraries.imdbquery.api.utils.Constants;
import com.web.libraries.imdbquery.api.utils.Logger;

/**
 * Thread Class that operates the recover of the moview of a single result request. This class is
 * responsible for the re-schedule of the accepted child pages.
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 * @since Java 1.8
 * @see Result
 * @see WebRestQueryScheduler
 * @see WebRestQuery
 * @see WebRestQueryException
 */
public class WebRestQueryProcess extends Thread {

	private Result searchContext;
	/*
	 * This is for scheduling multiple site access and adding multiple content ... (eg. getting pictures, page, etc..)
	 */
	@SuppressWarnings("unused")
	private WebRestQueryScheduler scheduler;
	
	private WebRestQueryConfiguration configuration;

	/**
	 * WebCrowler process constructor
	 * @param projectUID Project Unique Identifier
	 * @param searchContext Web Site Page to crawl into
	 * @param scheduler Child Web Page crawling scheduler
	 */
	public WebRestQueryProcess(String projectUID, Result searchContext, WebRestQueryScheduler scheduler) {
		super();
		this.searchContext = searchContext;
		this.scheduler = scheduler;
		configuration = WebRestQueryHelper.getProjectConfiguration(projectUID);
	}


	private void seek() throws IOException, InterruptedException {
		Thread.sleep(1000);
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> vars = new HashMap<>(0);
		vars.put("apiKey", configuration.getAPIDatabase().getAPIKey());
		vars.put("searchText", configuration.getMovieTitle().replace(" ", "+").replace("++", "+"));
		Logger.debug("Rest URL = " + configuration.getAPIDatabase().getQueryRestURL(), WebRestQueryProcess.class);
		String answer = restTemplate.getForObject(configuration.getAPIDatabase().getQueryRestURL(), String.class, vars);
		Map<String, String> resultMapping = configuration.getAPIDatabase().getFieldMapping();
		Logger.debug("Rest JSON = " + answer, WebRestQueryProcess.class);
		JSONObject object = new JSONObject(answer);
		JSONArray array = null;
		try {
			if (resultMapping!=null && resultMapping.get(Constants.MOVIE_PROPERTY_RESULTS)!=null && object.has(resultMapping.get(Constants.MOVIE_PROPERTY_RESULTS))) {
				String[] resultHierarchy = resultMapping.get(Constants.MOVIE_PROPERTY_RESULTS).split(",");
				int idx = 0;
				for (String result: resultHierarchy) {
					if (idx<resultHierarchy.length-1) {
						object = object.getJSONObject(result.trim());
					}
					else {
						array = object.getJSONArray(result.trim());
					}
					idx++;
				}
			}
		} catch (JSONException e) {
			Logger.warn("Movie not found ...", WebRestQueryProcess.class);
		}
		if (array!=null) {
			Logger.info("Found elements in the array = " + array.length(), WebRestQueryProcess.class);
			array.forEach(new Consumer<Object>() {

				/* (non-Javadoc)
				 * @see java.util.function.Consumer#accept(java.lang.Object)
				 */
				@Override
				public void accept(Object t) {
					try {
						Logger.debug(t.getClass() + " : Element : " + t, WebRestQueryProcess.class);
						JSONObject details=null;
						Object uid=null;
						String _url=null;
						String _tumbnail=null;
						String _title=null;
						if (JSONObject.class.isAssignableFrom(t.getClass()) && configuration.getAPIDatabase().getDetailsRestURL()!=null ) {
							JSONObject header = (JSONObject)t;
							if (header.has(resultMapping.get(Constants.MOVIE_PROPERTY_ID)))
								uid = ""+header.get(resultMapping.get(Constants.MOVIE_PROPERTY_ID));
							if (header.has(resultMapping.get(Constants.MOVIE_PROPERTY_URL)))
								_url = ""+header.get(resultMapping.get(Constants.MOVIE_PROPERTY_URL));
							if (header.has(resultMapping.get(Constants.MOVIE_PROPERTY_PICTURE)))
								_tumbnail = ""+header.get(resultMapping.get(Constants.MOVIE_PROPERTY_PICTURE));
							if (header.has(resultMapping.get(Constants.MOVIE_PROPERTY_TITLE)))
								_title = header.getString(resultMapping.get(Constants.MOVIE_PROPERTY_TITLE));
							Map<String, Object> var2 = header.toMap();
							var2.put("apiKey", configuration.getAPIDatabase().getAPIKey());
							String answer2 = restTemplate.getForObject(configuration.getAPIDatabase().getDetailsRestURL(), String.class, var2);
							System.out.println("answer2 = " + answer2);
							details = new JSONObject(answer2);
						}
						else if (JSONObject.class.isAssignableFrom(t.getClass())) {
							//No further query out object is the details
							details = (JSONObject)t;
							if (details.has(resultMapping.get(Constants.MOVIE_PROPERTY_ID)))
								uid = ""+details.get(resultMapping.get(Constants.MOVIE_PROPERTY_ID));
							if (details.has(resultMapping.get(Constants.MOVIE_PROPERTY_URL)))
								_url = ""+details.get(resultMapping.get(Constants.MOVIE_PROPERTY_URL));
							if (details.has(resultMapping.get(Constants.MOVIE_PROPERTY_PICTURE)))
								_tumbnail = ""+details.get(resultMapping.get(Constants.MOVIE_PROPERTY_PICTURE));
							if (details.has(resultMapping.get(Constants.MOVIE_PROPERTY_TITLE)))
								_title = details.getString(resultMapping.get(Constants.MOVIE_PROPERTY_TITLE));
						}
						String _director = null;
						String _year = null;
						if (details!=null) {
							if (resultMapping.get(Constants.MOVIE_PROPERTY_DETAIL_RESULTS)!=null) {
								String[] detailsResultHierarchy = resultMapping.get(Constants.MOVIE_PROPERTY_DETAIL_RESULTS).split(",");
								for (String result: detailsResultHierarchy) {
									details = details.getJSONObject(result.trim());
								}
							}
							if (uid==null && details.has(resultMapping.get(Constants.MOVIE_PROPERTY_ID))) {
								uid = ""+details.get(resultMapping.get(Constants.MOVIE_PROPERTY_ID));
							}
							if (_url==null && details.has(resultMapping.get(Constants.MOVIE_PROPERTY_URL))) {
								_url = ""+details.get(resultMapping.get(Constants.MOVIE_PROPERTY_URL));
							}
							if (_tumbnail==null && details.has(resultMapping.get(Constants.MOVIE_PROPERTY_PICTURE))) {
								_tumbnail = ""+details.get(resultMapping.get(Constants.MOVIE_PROPERTY_PICTURE));
							}
							if (_title==null && details.has(resultMapping.get(Constants.MOVIE_PROPERTY_TITLE))) {
								_title = details.getString(resultMapping.get(Constants.MOVIE_PROPERTY_TITLE));
							}
							StringBuffer _directorBuffer=new StringBuffer();
							if (resultMapping.get(Constants.MOVIE_PROPERTY_DIRECTOR)!=null && details.has(resultMapping.get(Constants.MOVIE_PROPERTY_DIRECTOR))) {
								_directorBuffer.append( details.getString(resultMapping.get(Constants.MOVIE_PROPERTY_DIRECTOR)) );
							}
							else if (resultMapping.get(Constants.MOVIE_PROPERTY_DIRECTORS)!=null && details.has(resultMapping.get(Constants.MOVIE_PROPERTY_DIRECTORS))) {
								JSONArray directorsArray = details.getJSONArray(resultMapping.get(Constants.MOVIE_PROPERTY_DIRECTORS));
								directorsArray.forEach(new Consumer<Object>() {

									/* (non-Javadoc)
									 * @see java.util.function.Consumer#accept(java.lang.Object)
									 */
									@Override
									public void accept(Object t) {
										_directorBuffer.append( (directorsArray.length()==0 ? "" : ",") + t);
									}

								});
							}
							_director = _directorBuffer.toString();
							_year = "0";
							if (resultMapping.get(Constants.MOVIE_PROPERTY_YEAR)!=null && details.has(resultMapping.get(Constants.MOVIE_PROPERTY_YEAR))) {
								Object tmp = details.get(resultMapping.get(Constants.MOVIE_PROPERTY_YEAR));
								if (tmp!=null && tmp.toString().trim().length()>0 && !tmp.toString().trim().equalsIgnoreCase("null")) {
									_year = ""+tmp;
									if (_year.indexOf("-")>0) {
										String[] tokens = _year.split("-");
										_year = tokens[0];
										for(String token: tokens) {
											if (token.trim().length()>=4) {
												_year = token.trim();
												break;
											}
										}
									}
									else if (_year.indexOf(".")>0) {
										String[] tokens = _year.split(".");
										_year = tokens[0];
										for(String token: tokens) {
											if (token.trim().length()>=4) {
												_year = token.trim();
												break;
											}
										}
									}
								}
							}
						}
						if (_url==null) {
							_url="";
						}
						if (_tumbnail==null) {
							_tumbnail="";
						}
						if (_year==null) {
							_year="0";
						}
						if (_title!=null && (!configuration.getExactSearch() ? _title.indexOf(configuration.getMovieTitle())>=0 : _title.trim().equalsIgnoreCase(configuration.getMovieTitle()) ) )  {
							ResultOutput output = new ResultOutput();
							output.put(Constants.MOVIE_PROPERTY_ID, uid);
							output.put(Constants.MOVIE_PROPERTY_TITLE, _title);
							output.put(Constants.MOVIE_PROPERTY_PICTURE, _tumbnail);
							output.put(Constants.MOVIE_PROPERTY_URL, _url);
							output.put(Constants.MOVIE_PROPERTY_DIRECTOR, _director );
							output.put(Constants.MOVIE_PROPERTY_YEAR, _year);
							output.put(Constants.MOVIE_PROPERTY_PROVIDER, configuration.getAPIDatabase().getSelector());
							/*
							 * Accumulation of the output movies and the transposition in the qualified relative 'Movie' Dto 
							 */
							searchContext.addResultOutput(output);
							/*Future purpose : scheduler to download mixed contents ...*/
							//scheduler.schedule(output);
						}
					} catch (Throwable throwable) {
						Logger.error("Error inspecting one result output", throwable, WebRestQueryProcess.class);
					}
				}

			});
		}
		else {
			Logger.info("Not parsable API no element found in the array ...", WebRestQueryProcess.class);
		}
	}


	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run()
	{
		try {
			this.seek();
		} catch (IOException|InterruptedException e) {
			Logger.error("Error exeuting the crawl operations ... " , e, WebRestQuery.class);
		}
	}

}
