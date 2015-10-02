package org.confetti.fet.xml.core;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * @author Bubla Gabor
 */
@XmlType(name = "hour_type")
public class HourXml {

	// --------------- fields ------------------------------------------------------------------------------------------
	private String name;

	// --------------- constructors ------------------------------------------------------------------------------------
	HourXml() {
	}
	
	public HourXml(String name) {
		this.name = name;
	}

	// --------------- getters and setters -----------------------------------------------------------------------------
	@XmlValue
	public String getName() 				{ return name; }
	public void setName(String name) 		{ this.name = name; }

}
