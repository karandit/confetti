package org.confetti.tests.tablix.dataprovider;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import org.confetti.tablix.dataprovider.TablixDataProvider;
import org.confetti.tablix.xml.TablixFAO;
import org.confetti.tablix.xml.TablixXml;
import org.junit.Test;

public class TablixDataProviderTest {
	
	private static InputStream openStream(final String path) throws Exception {
		URL uri = TablixDataProviderTest.class.getResource(path);
		return uri.openStream();
	}
	
	private static TablixDataProvider readFromFile(final String path) throws Exception {
		try (InputStream is = openStream(path)) {
			TablixXml xml = new TablixFAO().importFrom(is);
			return new TablixDataProvider(xml, new File(path));
		} 
	}

	@Test
	public void test_sample2() throws Exception {
		readFromFile("sample2-source.xml");
	}
	
	@Test
	public void test_tough2() throws Exception {
		readFromFile("tough2-source.xml");
	}
	
	@Test
	public void test_uni() throws Exception {
		readFromFile("uni-source.xml");
	}

}
