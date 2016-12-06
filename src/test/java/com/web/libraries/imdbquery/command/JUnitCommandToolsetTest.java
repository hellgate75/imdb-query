package com.web.libraries.imdbquery.command;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.apache.commons.lang.SystemUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.runners.MockitoJUnitRunner;

import com.web.libraries.imdbquery.api.WebRestQueryHelper;
import com.web.libraries.imdbquery.api.exception.WebRestQueryException;
import com.web.libraries.imdbquery.api.model.Result;
import com.web.libraries.imdbquery.api.model.ResultOutput;
import com.web.libraries.imdbquery.api.types.ResultCollector;
import com.web.libraries.imdbquery.api.types.ResultFormatter;
import com.web.libraries.imdbquery.api.utils.MovieMapper;
import com.web.libraries.imdbquery.command.collectors.StandarOutputResultCollector;
import com.web.libraries.imdbquery.command.formatters.PlainTextFormatter;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnitCommandToolsetTest {

	static {
		System.setProperty("log4j.configurationFile", WebRestQueryHelper.getLoggerConfigFile());
	}

	@Test
	public void testStandardOutputResultCollector() throws Exception {
		ResultFormatter formatter = new PlainTextFormatter();
		ResultCollector collector = new StandarOutputResultCollector();
		collector.setFormatter(formatter);
//		Result parentPage = Result.fromUrlString("http://www.facebook.com");
//		ByteArrayOutputStream bo = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(bo));
//        collector.exportPage(parentPage);
//        String printed = new String(bo.toByteArray(), "utf-8");
//        assertEquals("Web Parent Page must be well exported", parentPage.getFullUrlString() + (SystemUtils.IS_OS_WINDOWS ? "\r" : "")+"\n" + "  [-] http://www.google.com?s=java (NOT VISITED)" + (SystemUtils.IS_OS_WINDOWS ? "\r" : "") + "\n", printed );
//        bo.reset();

	}
	@Test
	public void testPlainTextFormatter() throws WebRestQueryException {
		ResultFormatter formatter = new PlainTextFormatter();
		String JSON="{\"title\": \"American Pie\", \"year\": 2005}";
		String OUTPUT="";
		ResultOutput parentResult = new ResultOutput(JSON);
		MovieMapper mapper = new MovieMapper(WebRestQueryHelper.getDefultApiDatabase());
//		assertEquals("Web Parent Page must be well formatted", OUTPUT, formatter.format(mapper.mapMovie(parentResult)));
		//assertTrue("A valid candidate must ");
	}
}
