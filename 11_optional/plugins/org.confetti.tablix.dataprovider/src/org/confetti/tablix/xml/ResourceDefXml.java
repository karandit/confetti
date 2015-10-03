package org.confetti.tablix.xml;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "resourcedef_type")
public class ResourceDefXml {
	
	private String name;
	private List<RestrictionXml> restrictions = new LinkedList<>();
	
	@XmlAttribute(name = "name")
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	@XmlElement(name = "restriction")
	public List<RestrictionXml> getRestrictions() { return restrictions; }
	public void setRestrictions(List<RestrictionXml> restrictions) { this.restrictions = restrictions; }
	
}
