package com.web.libraries.imdbquery.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import com.web.libraries.imdbquery.api.types.APIEngine;
import com.web.libraries.imdbquery.api.utils.Logger;

/**
 * Interface that realize a base API Engine Factory Controller.
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 * @since Java 1.8
 */
public class APIEngineFactory {
	private static List<APIEngine> engines = null;
	private final static Reflections engineReflections = new Reflections(new ConfigurationBuilder()
		     .setUrls(ClasspathHelper.forPackage("com.web.libraries.imdbquery.api.controller.engines"))
		     .setScanners(new SubTypesScanner())
		     .filterInputsBy(new FilterBuilder().includePackage("com.web.libraries.imdbquery.api.controller.engines")));


	private static final Set<Class<? extends APIEngine>> getEngineClasses() {
		return engineReflections.getSubTypesOf(APIEngine.class);
	}
	
	public static final List<APIEngine> getCollectors() {
		if (engines==null) {
			engines = new ArrayList<APIEngine>(0);
			for(Class<? extends APIEngine> clazz: getEngineClasses()) {
				try {
					APIEngine engine = clazz.newInstance();
					if (engine.isActive())
						engines.add(engine);
				} catch (InstantiationException|IllegalAccessException e) {
					Logger.error("Error retring class" + clazz, e, APIEngineFactory.class);
				}
			}
		}
		return engines;
	}

	/**
	 * Retrieve the API Engine to use by selector
	 * @param selector APIEngine selector string
	 * @return (List) the API Engine to use
	 */
	public APIEngine bySelector(String selector) {
		for (APIEngine engine :getCollectors()) {
			if (engine!=null && engine.isActive() && 
					engine.getSelector().equalsIgnoreCase(selector.trim()))
				return engine;
		}
		return getCollectors().get(0);
	}
	
	/**
	 * Retrieve the list of available mappings
	 * @return (List) the list of available mappings
	 */
	public List<APIEngine> getAvailableEngines() {
		return getCollectors();
	}
	
}
