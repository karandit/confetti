package org.confetti.xml.core;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * @author Bubla Gabor
 */
@XmlType(name = "subjectref_type")
public class SubjectRef {
	// --------------- fields ------------------------------------------------------------------------------------------
	private String name;
	// --------------- getters and setters -----------------------------------------------------------------------------
	@XmlValue
	public String getName() 				{ return name; }
	public void setName(String name) 		{ this.name = name; }
}
