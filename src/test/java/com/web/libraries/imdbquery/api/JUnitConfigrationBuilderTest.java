package com.web.libraries.imdbquery.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.commons.lang.SystemUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.runners.MockitoJUnitRunner;

import com.web.libraries.imdbquery.api.configuration.WebRestQueryConfiguration;
import com.web.libraries.imdbquery.api.configuration.WebRestQueryConfigurationBuilder;
import com.web.libraries.imdbquery.api.types.APIEngine;
import com.web.libraries.imdbquery.api.utils.Logger;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnitConfigrationBuilderTest {

	static {
		System.setProperty("log4j.configurationFile", WebRestQueryHelper.getLoggerConfigFile());
	}
	
	@Test
	public void testConfigrationBuilderFeature() {
		WebRestQueryConfigurationBuilder builder = new WebRestQueryConfigurationBuilder();
		APIEngine apiDb = WebRestQueryHelper.getDefultApiDatabase();
		String title = "Indiana Jones";
		String formatter = "plain";
		String outputType = "stdout";
		Integer thredExtends = 10;
		WebRestQueryConfiguration configuration = builder.apiDatabase(apiDb).movieTitle(title).formatter(formatter)
		.outputType(outputType).thredExtends(thredExtends).build();
		assertNotNull("Web Crawler Configuration must be not null", configuration);
		assertEquals("API Dtabase must be the same passed to the Builder", apiDb, configuration.getAPIDatabase());
		assertEquals("Output Type must be the same passed to the Builder", outputType, configuration.getOutputType());
		assertEquals("Output Formatter must be the same passed to the Builder", formatter, configuration.getFormatter());
		assertEquals("Movie Title level must be the same passed to the Builder", title, configuration.getMovieTitle());
		assertEquals("Thread Extends must be the same passed to the Builder", thredExtends, configuration.getThredExtends());
	}

	
	@Test
	public void testLoggerErrorWarningDebugInformationMessage() throws IOException {
		String message = "My message";
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        int line = 17;
		Logger.info(message, JUnitConfigrationBuilderTest.class);
        String printed = new String(bo.toByteArray(), "utf-8");
        String out1 = "INFO  Logger:"+line+" - Log for com.web.libraries.imdbquery.api.JUnitConfigrationBuilderTest :: "+message+(SystemUtils.IS_OS_WINDOWS ? "\r" : "")+"\n";
        //assertEquals("Must be an appropriate INFO output", printed.substring(20), out1 );
        bo.reset();
		line = 25;
		Logger.warn(message, JUnitConfigrationBuilderTest.class);
        printed = new String(bo.toByteArray(), "utf-8");
        out1 = "WARN  Logger:"+line+" - Log for com.web.libraries.imdbquery.api.JUnitConfigrationBuilderTest :: "+message+(SystemUtils.IS_OS_WINDOWS ? "\r" : "")+"\n";
        //assertEquals("Must be an appropriate WARN output", printed.substring(20), out1 );
        bo.reset();
		line = 33;
		Logger.debug(message, JUnitConfigrationBuilderTest.class);
        printed = new String(bo.toByteArray(), "utf-8");
        out1 = "DEBUG Logger:"+line+" - Log for com.web.libraries.imdbquery.api.JUnitConfigrationBuilderTest :: "+message+(SystemUtils.IS_OS_WINDOWS ? "\r" : "")+"\n";
        //assertEquals("Must be an appropriate DEBUG output", printed.substring(20), out1 );
        bo.reset();
		line = 41;
		Logger.error(message, JUnitConfigrationBuilderTest.class);
        printed = new String(bo.toByteArray(), "utf-8");
        out1 = "ERROR Logger:"+line+" - Log for com.web.libraries.imdbquery.api.JUnitConfigrationBuilderTest :: "+message+(SystemUtils.IS_OS_WINDOWS ? "\r" : "")+"\n";
        //assertEquals("Must be an appropriate ERROR output", printed.substring(20), out1 );
        bo.reset();
		line = 50;
		Exception ex = new RuntimeException(message);
		Logger.error(message, ex, JUnitConfigrationBuilderTest.class);
        printed = new String(bo.toByteArray(), "utf-8");
        out1 = "ERROR Logger:"+line+" - Log for com.web.libraries.imdbquery.api.JUnitConfigrationBuilderTest :: "+message.substring(0, 4);
        //assertEquals("Must be an appropriate ERROR output", printed.substring(20, 115), out1 );
	}
}
