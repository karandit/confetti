package org.confetti.tablix.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(name = "restriction_type")
public class RestrictionXml {

	private String type;
	private String value;
	
	@XmlAttribute(name = "type")
	public String getType() { return type; }
	public void setType(String type) { this.type = type; }

	@XmlValue
	public String getValue() { return value; }
	public void setValue(String value) { this.value = value; }

}
