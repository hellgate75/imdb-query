package com.web.libraries.imdbquery.api;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.runners.MockitoJUnitRunner;

import com.web.libraries.imdbquery.api.exception.WebRestQueryException;
import com.web.libraries.imdbquery.api.model.Result;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnitWebPageTest {

	static {
		System.setProperty("log4j.configurationFile", WebRestQueryHelper.getLoggerConfigFile());
	}

	@Test
	public void testRootWebPageAccessor() throws WebRestQueryException {
//		Result aPage = Result.fromUrlString("http://www.google.com");
//		assertEquals(aPage.getFullUrlString(), "http://www.google.com");
//		assertEquals("Protocol must be the same that the original", "http", aPage.getProtocol());
//		assertEquals("Port must be -1 (not assigned)", -1, aPage.getPort());
//		assertEquals("Host name must be the same that the original", "www.google.com", aPage.getHost());
	}
}
