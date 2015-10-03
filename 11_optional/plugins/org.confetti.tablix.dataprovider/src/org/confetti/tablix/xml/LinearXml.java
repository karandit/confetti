package org.confetti.tablix.xml;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "linear_type")
public class LinearXml {

	private String name;
	private int from;
	private int to;
	private List<RestrictionXml> restrictions = new LinkedList<>();
	
	@XmlAttribute(name = "name")
	public String getName() 						{ return name; }
	public void setName(String name) 				{ this.name = name; }
	
	@XmlAttribute
	public int getFrom() { return from; }
	public void setFrom(int from) { this.from = from; }
	
	@XmlAttribute
	public int getTo() { return to; }
	public void setTo(int to) { this.to = to; }
	
	@XmlElement(name = "restriction")
	public List<RestrictionXml> getRestrictions() { return restrictions; }
	public void setRestrictions(List<RestrictionXml> restrictions) { this.restrictions = restrictions; }
	
}
