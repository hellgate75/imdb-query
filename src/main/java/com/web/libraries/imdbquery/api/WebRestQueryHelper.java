package com.web.libraries.imdbquery.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import com.web.libraries.imdbquery.api.configuration.WebRestQueryConfiguration;
import com.web.libraries.imdbquery.api.configuration.WebRestQueryConfigurationBuilder;
import com.web.libraries.imdbquery.api.controller.APIEngineFactory;
import com.web.libraries.imdbquery.api.exception.WebRestQueryException;
import com.web.libraries.imdbquery.api.types.APIEngine;
import com.web.libraries.imdbquery.api.types.ResultCollector;
import com.web.libraries.imdbquery.api.types.ResultFormatter;
import com.web.libraries.imdbquery.api.utils.Constants;
import com.web.libraries.imdbquery.api.utils.Logger;

/**
 * Web Crawler Helper Class
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 * @since Java 1.8
 */
public class WebRestQueryHelper {
	private static Properties webRestQueryProperties = new Properties();
	private static Map<String, WebRestQueryConfiguration> projectsConfigurationMap = new LinkedHashMap<String, WebRestQueryConfiguration>(0);
	private final static Reflections collectorsReflections = new Reflections(new ConfigurationBuilder()
		     .setUrls(ClasspathHelper.forPackage("com.web.libraries.imdbquery.command.collectors"))
		     .setScanners(new SubTypesScanner())
		     .filterInputsBy(new FilterBuilder().includePackage("com.web.libraries.imdbquery.command.collectors")));

	private final static Reflections formattersReflections = new Reflections(new ConfigurationBuilder()
		     .setUrls(ClasspathHelper.forPackage("com.web.libraries.imdbquery.command.formatters"))
		     .setScanners(new SubTypesScanner())
		     .filterInputsBy(new FilterBuilder().includePackage("com.web.libraries.imdbquery.command.formatters")));
	
	static {
		try (InputStream propertiesInputStream = ClassLoader.getSystemResourceAsStream("application.properties");) {
			try {
				Logger.debug("InputStream = " + propertiesInputStream, WebRestQueryHelper.class);
				webRestQueryProperties.load(propertiesInputStream);
				
			} catch (IOException | NullPointerException e) {
				Logger.error("Error loading default properties",e, WebRestQueryHelper.class);
			}
		} catch (IOException e) {
			Logger.error("Error loading default properties",e, WebRestQueryHelper.class);
		}
	}

	private static APIEngineFactory defaultApiFactory = new APIEngineFactory();

	/**
	 * Retrieve the list of api query engine default properties
	 * @return list of default properties
	 */
	public static final Properties getDefaultApiQueryProperties() {
		return webRestQueryProperties;
	}
	
	
	/**
	 * Parse the comand line arguments and create and execute the crawler or print the help.
	 * In case of needeing the usage is available typing as argument : --help
	 * @param arguments command line arguments
	 * @throws WebRestQueryException Exception occurring during the Crawler execution
	 */
	public static final void parseParametersAndRunCrawler(String[] arguments) throws WebRestQueryException {
		WebRestQueryConfigurationBuilder builder = new WebRestQueryConfigurationBuilder();
		for(String argument: arguments) {
			if (argument.toLowerCase().indexOf("--" + Constants.COMMAND_PROPERTY_HELP_REQUEST)>=0) {
				printWebRESTAPIUsage(System.out);
				System.exit(0);
			}
			else if (argument.toLowerCase().indexOf("--" + Constants.COMMAND_PROPERTY_API_DB + "=")>=0) {
				try {
					
					builder.apiDatabase( defaultApiFactory.bySelector(argument.substring(argument.indexOf("=")+1)) );
				} catch (NullPointerException e) {
					String message = "Error parsing parameter '"+Constants.COMMAND_PROPERTY_API_DB+"'";
					Logger.error(message, e, WebRestQueryHelper.class);
					printWebRESTAPIUsage(System.out, message);
				}
			}
			else if (argument.toLowerCase().indexOf("--" + Constants.COMMAND_PROPERTY_MOVIE_TITLE + "=")>=0) {
				try {
					builder.movieTitle( argument.substring(argument.indexOf("=")+1) );
				} catch (NullPointerException e) {
					String message = "Error parsing parameter '"+Constants.COMMAND_PROPERTY_MOVIE_TITLE+"'";
					Logger.error(message, e, WebRestQueryHelper.class);
					printWebRESTAPIUsage(System.out, message);
				}
			}
			else if (argument.toLowerCase().indexOf("--" + Constants.COMMAND_PROPERTY_EXACT_SEARCH + "=")>=0) {
				try {
					builder.exactSearch( Boolean.parseBoolean(argument.substring(argument.indexOf("=")+1)) );
				} catch (NullPointerException e) {
					String message = "Error parsing parameter '"+Constants.COMMAND_PROPERTY_EXACT_SEARCH+"'";
					Logger.error(message, e, WebRestQueryHelper.class);
					printWebRESTAPIUsage(System.out, message);
				}
			}
			else if (argument.toLowerCase().indexOf("--" + Constants.COMMAND_PROPERTY_PARALLEL_THREADS + "=")>=0) {
				try {
					builder.thredExtends( Integer.parseInt(argument.substring(argument.indexOf("=")+1)) );
				} catch (NumberFormatException|NullPointerException e) {
					String message = "Error parsing parameter '"+Constants.COMMAND_PROPERTY_PARALLEL_THREADS+"'";
					Logger.error(message, e, WebRestQueryHelper.class);
					printWebRESTAPIUsage(System.out, message);
				}
			}
			else if (argument.toLowerCase().indexOf("--" + Constants.COMMAND_PROPERTY_REPORT_FORMATTER + "=")>=0) {
				try {
					builder.formatter( argument.substring(argument.indexOf("=")+1) );
				} catch (NullPointerException e) {
					String message = "Error parsing parameter '"+Constants.COMMAND_PROPERTY_REPORT_FORMATTER+"'";
					Logger.error(message, e, WebRestQueryHelper.class);
					printWebRESTAPIUsage(System.out, message);
				}
			}
			else if (argument.toLowerCase().indexOf("--" + Constants.COMMAND_PROPERTY_REPORT_COLLETOR + "=")>=0) {
				try {
					builder.outputType( argument.substring(argument.indexOf("=")+1) );
				} catch (NullPointerException e) {
					String message = "Error parsing parameter '"+Constants.COMMAND_PROPERTY_REPORT_COLLETOR+"'";
					Logger.error(message, e, WebRestQueryHelper.class);
					printWebRESTAPIUsage(System.out, message);
				}
			}
		}
		if (System.getProperties().containsKey(Constants.COMMAND_PROPERTY_API_DB)) {
			try {
				builder.apiDatabase(defaultApiFactory.bySelector((String)System.getProperties().get(Constants.COMMAND_PROPERTY_API_DB)));
			} catch (NullPointerException e) {
				builder.apiDatabase(getDefultApiDatabase());
			}
		}
		if (System.getProperties().containsKey(Constants.COMMAND_PROPERTY_EXACT_SEARCH)) {
			try {
				builder.exactSearch( Boolean.parseBoolean((String)System.getProperties().get(Constants.COMMAND_PROPERTY_EXACT_SEARCH)) );
			} catch (NullPointerException e) {
				builder.exactSearch(getDefultExactSearch());
			}
		}
		if (System.getProperties().containsKey(Constants.COMMAND_PROPERTY_MOVIE_TITLE)) {
			builder.movieTitle((String)System.getProperties().get(Constants.COMMAND_PROPERTY_MOVIE_TITLE));
		}
		WebRestQueryConfiguration configuration = builder.build();
		ResultCollector collector=getCollector(configuration.getOutputType(),configuration.getFormatter());
		Logger.debug("configuration api db = " + configuration.getAPIDatabase(), WebRestQueryHelper.class);
		Logger.debug("configuration search for = " + configuration.getMovieTitle(), WebRestQueryHelper.class);
		new WebRestQuery(collector, configuration).start();
	}
	
	/**
	 * Print the help usage text in a specific output stream
	 * @param output The select help stream
	 */
	public static final void printWebRESTAPIUsage(PrintStream output)  {
		printWebRESTAPIUsage(output, null);
	}
	
	public static final String getLoggerConfigFile() {
		return (String)webRestQueryProperties.get("logging.config.path");
	}
	/**
	 * Print the help usage text in a specific output stream
	 * @param output The select help stream
	 * @param message Message to display
	 */
	public static final void printWebRESTAPIUsage(PrintStream output, String message)  {
		if(message!=null) {
			output.println("Message: " + message);
		}
		output.println("Usage: api-search [options]");
		output.println("samples: api-search --format=simple --api imdb --movie \"Indiana Jones\"");
		output.println("         api-search --movie \"Herry Potter\"");
		output.println("options:");
		output.println("--"+Constants.COMMAND_PROPERTY_REPORT_FORMATTER+"\t\tIdentifies the site map format");
		output.println("     available formats :");
		for (ResultFormatter formatter: getFormatters())
			output.println("     " + formatter.getSelector() + "\t\t" + formatter.getDescription());
		output.println("default : " + getDefultFormatter());
		output.println("--"+Constants.COMMAND_PROPERTY_REPORT_COLLETOR+"\t\tIdentifies output device");
		output.println("     available output :");
		for (ResultCollector collector: getCollectors())
			output.println("     " + collector.getSelector() + "\t\t" + collector.getDescription());
		output.println("default : " + getDefultOutputType());
		output.println("--"+Constants.COMMAND_PROPERTY_PARALLEL_THREADS+"\t\tIdentifies the number of threads extension on the surf. This is the minimum number of threads");
		output.println("\t\t\trunning on the site hierarchy discovery");
		output.println("default : " + getDefultThredExtends());
		output.println("-D"+Constants.COMMAND_PROPERTY_MOVIE_TITLE+"=<value>\t\tIdentifies the movie title query name");
		output.println("--"+Constants.COMMAND_PROPERTY_MOVIE_TITLE+"\t\tIdentifies the movie title query name");
		output.println("default : " + getDefultMovieTitle());
		output.println("-D"+Constants.COMMAND_PROPERTY_EXACT_SEARCH+"=<value>\t\tIdentifies the exact title search");
		output.println("--"+Constants.COMMAND_PROPERTY_EXACT_SEARCH+"\t\tIdentifies the exact title search");
		output.println("default : " + getDefultExactSearch());
		output.println("-D"+Constants.COMMAND_PROPERTY_API_DB+"=<value>\t\tIdentifies the default used API database");
		output.println("--"+Constants.COMMAND_PROPERTY_API_DB+"\t\tIdentifies the default used API database");
		output.println("     available engines :");
		for (APIEngine engine: defaultApiFactory.getAvailableEngines()) {
			output.println("     " + engine.getSelector() + "\t\t" + engine.getDescription());
		}
		output.println("default : " + getDefultApiDatabase());
	}
	
	/**
	 * Retrieves the default movie title
	 * @return The default movie Title
	 */
	public static final String getDefultMovieTitle() {
		if (webRestQueryProperties!=null && webRestQueryProperties.containsKey(Constants.PROPERTIES_DEFAULT_MOVIE_TITLE))
			return (String)webRestQueryProperties.get(Constants.PROPERTIES_DEFAULT_MOVIE_TITLE);
		else
			return null;
	}

	/**
	 * Retrieves the default web site URL
	 * @return The default web site URL
	 */
	public static final APIEngine getDefultApiDatabase() {
		if (webRestQueryProperties!=null && webRestQueryProperties.containsKey(Constants.PROPERTIES_DEFAULT_API_DATABASE))
			return defaultApiFactory.bySelector((String)webRestQueryProperties.get(Constants.PROPERTIES_DEFAULT_API_DATABASE));
		else
			return defaultApiFactory.bySelector(Constants.PROPERTIES_DEFAULT_API_DATABASE);
	}
	
	/**
	 * Retrieves the default site map response formatter
	 * @return The default site map response formatter
	 */
	public static final String getDefultFormatter() {
		return (String)webRestQueryProperties.getOrDefault(Constants.PROPERTIES_DEFAULT_REPORT_FORMATTER, "plain");
	}
	
	/**
	 * Retrieves the default output device
	 * @return The default output device
	 */
	public static final String getDefultOutputType() {
		return (String)webRestQueryProperties.getOrDefault(Constants.PROPERTIES_DEFAULT_REPORT_COLLECTOR, "stdout");
	}
	
	
	/**
	 * Retrieves the default thread extends to execute the Web Site Pages surfing.
	 * @return The default thread extends
	 */
	public static final Integer getDefultThredExtends() {
		try {
			return Integer.parseInt((String)webRestQueryProperties.getOrDefault(Constants.PROPERTIES_DEFAULT_PARALLEL_THREADS, "10"));
		} catch (NumberFormatException e) {
			return 10;
		}
	}
	
	/**
	 * Retrieves the default thread extends to execute the Web Site Pages surfing.
	 * @return The default thread extends
	 */
	public static final Boolean getDefultExactSearch() {
		try {
			return (Integer.parseInt((String)webRestQueryProperties.getOrDefault(Constants.PROPERTIES_DEFAULT_PARALLEL_THREADS, "1"))) == 1;
		} catch (NumberFormatException e) {
			return Boolean.TRUE;
		}
	}
	
	/**
	 * Register a project in the registry. 
	 * This feature at the moment is a simple in memory registry, it will be provided a persistent registry in the future 
	 * to allow the off line execution, the reschedule and the access from remote services.
	 * @param configuration Web Crawler Project Configuration
	 * @return The Project Unique ID
	 */
	public static String regiterWebCrawerProject(WebRestQueryConfiguration configuration) {
		String projectUID = UUID.randomUUID().toString();
		projectsConfigurationMap.put(projectUID, configuration);
		return projectUID;
	}
	
	/**
	 * Retrieves a Web Crawler Project configuration from the registry
	 * @param projectUID Project Unique ID
	 * @return The selected configuration or null i the UID doesn't exists in the registry
	 */
	public static final WebRestQueryConfiguration getProjectConfiguration(String projectUID) {
		return projectsConfigurationMap.get(projectUID);
	}
	
	private static final Set<Class<? extends ResultCollector>> getCollectorClasses() {
		return collectorsReflections.getSubTypesOf(ResultCollector.class);
	}
	
	private static final Set<Class<? extends ResultFormatter>> getFormatterClasses() {
		return formattersReflections.getSubTypesOf(ResultFormatter.class);
	}
	

	/**
	 * Extract a complete ResultCollector, provided of Formatted according to the provided selectors. In case of mistakes 
	 * the system try to provide the default ones.
	 * @param collectorSelector Selector used to retrieve a ResultCollector
	 * @param formatterSelector Selector used to retrieve a ResultFormatter
	 * @return ResultCollector according to the selectors or the retry policies
	 * @see ResultCollector
	 * @see ResultFormatter
	 */
	public static final ResultCollector getCollector(String collectorSelector, String formatterSelector) {
		return getCollector(collectorSelector, formatterSelector, false);
	}
	
	private static final ResultCollector getCollector(String collectorSelector, String formatterSelector, boolean retry) {
		ResultFormatter formatter = null;
		for(ResultFormatter discoveredFormatter: getFormatters()) {
			if (discoveredFormatter.getSelector().equalsIgnoreCase(formatterSelector)) {
				formatter = discoveredFormatter;
				break;
			}
		}
		if (formatter==null) {
			if (!retry)
				return getCollector(collectorSelector, getDefultFormatter(), true);
			else 
				return null;
		}
		ResultCollector collector = null;
		for(ResultCollector discoveredCollector: getCollectors()) {
			if (discoveredCollector.getSelector().equalsIgnoreCase(collectorSelector)) {
				collector = discoveredCollector;
				collector.setFormatter(formatter);
				break;
			}
		}
		if (collector==null) {
			if (!retry) 
				return getCollector(getDefultOutputType(), formatterSelector, true);
			else 
				return null;
		}
		return collector;
	}
	
	/**
	 * Return the list of the available and active collectors
	 * @return the list of collectors
	 * @see ResultCollector
	 */
	public static final List<ResultCollector> getCollectors() {
		List<ResultCollector> collectors = new ArrayList<ResultCollector>(0);
		for(Class<? extends ResultCollector> clazz: getCollectorClasses()) {
			try {
				ResultCollector collector = clazz.newInstance();
				if (collector.isActive())
					collectors.add(collector);
			} catch (InstantiationException|IllegalAccessException e) {
				Logger.error("Error retring class" + clazz, e, WebRestQueryHelper.class);
			}
		}
		return collectors;
	}
	
	/**
	 * Return the list of the available and active formatters
	 * @return the list of formatters
	 * @see ResultFormatter
	 */
	public static final List<ResultFormatter> getFormatters() {
		List<ResultFormatter> formatters = new ArrayList<ResultFormatter>(0);
		for(Class<? extends ResultFormatter> clazz: getFormatterClasses()) {
			try {
				ResultFormatter formatter = clazz.newInstance();
				if (formatter.isActive())
					formatters.add(formatter);
			} catch (InstantiationException|IllegalAccessException e) {
				Logger.error("Error retring class" + clazz, e, WebRestQueryHelper.class);
			}
		}
		return formatters;
	}
	
}
