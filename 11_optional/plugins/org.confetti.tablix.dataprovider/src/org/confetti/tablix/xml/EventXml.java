package org.confetti.tablix.xml;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "event_type")
public class EventXml {
	
	private String name;
	private int repeats;
	private List<ResourceXml> resources = new LinkedList<>();
	private List<RestrictionXml> restrictions = new LinkedList<>();
	
	@XmlAttribute(name = "name")
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	@XmlAttribute(name = "repeats")
	public int getRepeats() { return repeats; }
	public void setRepeats(int repeats) { this.repeats = repeats; }

	@XmlElement(name = "resource")
	public List<ResourceXml> getResources() { return resources; }
	public void setResources(List<ResourceXml> resources) { this.resources = resources; }

	@XmlElement(name = "restriction")
	public List<RestrictionXml> getRestrictions() { return restrictions; }
	public void setRestrictions(List<RestrictionXml> restrictions) { this.restrictions = restrictions; }

}
