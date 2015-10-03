package org.confetti.tablix.xml;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "module_type")
public class ModuleXml {
	
	private String name;
	private int weight;
	private String mandatory;
	private List<OptionXml> options = new LinkedList<>();

	@XmlAttribute(name = "name")
	public String getName() 						{ return name; }
	public void setName(String name) 				{ this.name = name; }
	
	@XmlAttribute(name = "weight")
	public int getWeight() 							{ return weight; }
	public void setWeight(int weight) 				{ this.weight = weight; }
	
	@XmlAttribute(name = "mandatory")
	public String isMandatory() 					{ return mandatory; }
	public void setMandatory(String mandatory) 		{ this.mandatory = mandatory; }
	
	@XmlElement(name = "option")
	public List<OptionXml> getOptions() 			{ return options; }
	public void setOptions(List<OptionXml> options) { this.options = options; }
	
}
