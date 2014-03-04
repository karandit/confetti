package org.confetti.xml;

import org.confetti.xml.core.InstituteXml;

public class InstituteFAO extends GenericFAO<InstituteXml> {

	/** The classes bound by this FAO. */
	private static final Class[] CLASSES = new Class[] {
		InstituteXml.class, 
		};

	@Override
	protected Class<?>[] getBoundedClasses() {
		return CLASSES;
	}

//	@Override
//	protected URL getSchemaURL() {
//		return null;
//	}

}
