package org.confetti.xml.core;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlType(name = "hour_type")
public class Hour_v5_24_0_Xml {

	// --------------- fields ------------------------------------------------------------------------------------------
	private String name;

	// --------------- constructors ------------------------------------------------------------------------------------
	Hour_v5_24_0_Xml() {
	}
	
	public Hour_v5_24_0_Xml(String name) {
		this.name = name;
	}

	// --------------- getters and setters -----------------------------------------------------------------------------
	@XmlElement(name = "Name")
	public String getName() 				{ return name; }
	public void setName(String name) 		{ this.name = name; }

}
