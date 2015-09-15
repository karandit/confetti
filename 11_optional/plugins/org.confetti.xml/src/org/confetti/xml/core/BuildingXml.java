package org.confetti.xml.core;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlType(name = "building_type")
public class BuildingXml {

	private String name;

	BuildingXml() {
	}
	
	public BuildingXml(String name) {
		this.name = name;
	}

	//	@XmlID
	@XmlElement(name = "Name")
	public String getName() 				{ return name; }
	public void setName(String name) 		{ this.name = name; }

}
