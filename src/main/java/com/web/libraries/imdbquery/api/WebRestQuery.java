package com.web.libraries.imdbquery.api;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import com.web.libraries.imdbquery.api.configuration.WebRestQueryConfiguration;
import com.web.libraries.imdbquery.api.configuration.WebRestQueryConfigurationBuilder;
import com.web.libraries.imdbquery.api.exception.WebRestQueryException;
import com.web.libraries.imdbquery.api.model.Result;
import com.web.libraries.imdbquery.api.model.ResultOutput;
import com.web.libraries.imdbquery.api.types.ResultCollector;
import com.web.libraries.imdbquery.api.types.WebRestQueryScheduler;
import com.web.libraries.imdbquery.api.utils.Logger;
import com.web.libraries.imdbquery.api.utils.MovieMapper;
import com.web.libraries.imdbquery.command.collectors.StandarOutputResultCollector;
import com.web.libraries.imdbquery.command.formatters.PlainTextFormatter;

/**
 * Web Crawler executor. It is the commander that schedule the processes and it is the responsible of the
 * requirements and profile information collection 
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 * @since Java 1.8
 * @see Result
 * @see WebRestQueryProcess
 * @see WebRestQueryException
 */
public class WebRestQuery implements WebRestQueryScheduler, IntConsumer {
	
	private Result searchContext;
	private String projectUID;
	
	private ResultCollector collector;
	private Queue<Result> siteQueue = new ConcurrentLinkedQueue<>() ;
	private WebRestQueryConfiguration configuration;
	
	/**
	 * Default Constructor
	 * @param collector Result Collector Instance
	 * @param configuration Web Crawling Project Configuration
	 * @throws WebRestQueryException Thrown when any crawling exception occures
	 */
	public WebRestQuery(ResultCollector collector, WebRestQueryConfiguration configuration) throws WebRestQueryException {
		super();
		Logger.info("API DATABASE : " + configuration.getAPIDatabase(), WebRestQuery.class);
		Logger.info("MOVIE TITLE : " + configuration.getMovieTitle(), WebRestQuery.class);
		Logger.debug("*****************************", WebRestQuery.class);
		Logger.debug("STARTING URL CROWL ....", WebRestQuery.class);
		Logger.debug("*****************************", WebRestQuery.class);
		this.projectUID = WebRestQueryHelper.regiterWebCrawerProject(configuration);
		this.collector = collector;
		this.configuration = configuration;
		this.searchContext = new Result(configuration.getMovieTitle(), new MovieMapper(configuration.getAPIDatabase()));
	}
	
	/**
	 * Start the crawling operations
	 */
	public void start() {
		startQueries(searchContext);
		consumeQueue();
	}
	
	/* (non-Javadoc)
	 * @see com.web.libraries.imdbquery.api.types.WebRestQueryScheduler#schedule(com.web.libraries.imdbquery.api.model.Result)
	 */
	@Override
	public void schedule(ResultOutput site) {
		/*
		 * Future : Implement the scheduler that seek for further information of the detailed movie document (eg. Image bytes, html content of the page, etc...)
		 */
	}
	
	/* (non-Javadoc)
	 * @see java.util.function.IntConsumer#accept(int)
	 */
	@Override
	public void accept(int index) {
		/*Implementation to manage a future version with a loader for multiple IMovie Databases */
		if (siteQueue.size()>0) {
			Logger.debug("Creating thread (index : "+index+") - queue size: " + siteQueue.size(), WebRestQuery.class);
		}
		startQueries(siteQueue.poll());
	}

	private void startQueries(Result site) {
		if (site!= null) {
			WebRestQueryProcess process = new WebRestQueryProcess(this.projectUID, site, this);
			process.start();
		}
	}
	
	private void writeReport() {
		writeResults(this.searchContext, collector);
	}

	private static void writeResults(Result sitePage, ResultCollector collector) {
		try {
			collector.exportPage(sitePage);
		} catch (WebRestQueryException e) {
			Logger.error("Error during the export of the Web API Query ...", WebRestQuery.class);
		}
	}
	
	private synchronized void consumeQueue() {
		do {
			if(Thread.activeCount()-1<this.configuration.getThredExtends() ) {
				IntStream.range(0, this.configuration.getThredExtends()).forEach(this);
			}
			else {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} while(!siteQueue.isEmpty() || Thread.activeCount()>1);
		Logger.debug("*****************************", WebRestQuery.class);
		Logger.debug("COMPLETED URL CROWL !!", WebRestQuery.class);
		Logger.debug("PRINTING RESULTS", WebRestQuery.class);
		Logger.debug("*****************************", WebRestQuery.class);
		writeReport();
	}
	
	public static void main(String[] args) throws Throwable {
		ResultCollector collector = new StandarOutputResultCollector();
		collector.setFormatter(new PlainTextFormatter());
		new WebRestQuery(collector, new WebRestQueryConfigurationBuilder().apiDatabase(WebRestQueryHelper.getDefultApiDatabase()).movieTitle("Indiana Jones").build()).start();
	}
	
}
