package org.confetti.tablix.xml;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "resources_type")
public class ResourcesXml {
	
	private List<ResourceTypeXml> constantTypes = new LinkedList<>();
	private List<ResourceTypeXml> variableTypes = new LinkedList<>();
	
	@XmlElementWrapper(name = "constant")
	@XmlElement(name = "resourcetype")
	public List<ResourceTypeXml> getConstantTypes() { return constantTypes; }
	public void setConstantTypes(List<ResourceTypeXml> constantTypes) { this.constantTypes = constantTypes; }
	
	@XmlElementWrapper(name = "variable")
	@XmlElement(name = "resourcetype")
	public List<ResourceTypeXml> getVariableTypes() { return variableTypes; }
	public void setVariableTypes(List<ResourceTypeXml> variableTypes) { this.variableTypes = variableTypes; }
	
}
