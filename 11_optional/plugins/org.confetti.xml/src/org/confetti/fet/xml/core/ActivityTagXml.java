package org.confetti.fet.xml.core;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "activitytag_type")
public class ActivityTagXml {

	// --------------- fields ------------------------------------------------------------------------------------------
	private String name;

	// --------------- constructors ------------------------------------------------------------------------------------
	ActivityTagXml() {
	}
	
	public ActivityTagXml(String name) {
		this.name = name;
	}

	// --------------- getters and setters -----------------------------------------------------------------------------
	@XmlElement(name = "Name")
	public String getName() 				{ return name; }
	public void setName(String name) 		{ this.name = name; }

}
