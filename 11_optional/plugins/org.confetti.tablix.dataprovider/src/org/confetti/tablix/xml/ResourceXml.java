package org.confetti.tablix.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "resource_type")
public class ResourceXml {
	private String type;
	private String name;

	@XmlAttribute(name = "type")
	public String getType() 			{ return type; }
	public void setType(String type) 	{ this.type = type; }
	
	@XmlAttribute(name = "name")
	public String getName() 			{ return name; }
	public void setName(String name) 	{ this.name = name; }

}
