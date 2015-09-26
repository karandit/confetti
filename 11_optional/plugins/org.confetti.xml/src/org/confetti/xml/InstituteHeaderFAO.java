package org.confetti.xml;

import java.net.URL;

import org.confetti.xml.header.InstituteHeaderXml;

public class InstituteHeaderFAO extends GenericFAO<InstituteHeaderXml> {

	/** The classes bound by this FAO. */
	private static final Class<?>[] CLASSES = new Class[] {
		InstituteHeaderXml.class, 
		};

	@Override
	protected Class<?>[] getBoundedClasses() {
		return CLASSES;
	}

	@Override
	protected URL getSchemaURL() {
		return null;
	}
	
}
