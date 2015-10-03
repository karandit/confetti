package org.confetti.tablix.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(name = "option_type")
public class OptionXml {
	private String name;
	private String value;

	@XmlAttribute(name = "name")
	public String getName() 						{ return name; }
	public void setName(String name) 				{ this.name = name; }
	
	@XmlValue
	public String getValue() { return value; }
	public void setValue(String value) { this.value = value; }
}
