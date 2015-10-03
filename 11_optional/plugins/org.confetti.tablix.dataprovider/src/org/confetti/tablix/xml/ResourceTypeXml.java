package org.confetti.tablix.xml;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "resourcetype_type", propOrder = {"resourceDefs", "matrices", "linears"})
public class ResourceTypeXml {
	
	private String type;
	private List<ResourceDefXml> resourceDefs = new LinkedList<>();
	private List<MatrixXml> matrices = new LinkedList<>();
	private List<LinearXml> linears = new LinkedList<>();
	
	@XmlAttribute(name = "type")
	public String getType() { return type; }
	public void setType(String type) { this.type = type; }
	
	@XmlElement(name = "resource")
	public List<ResourceDefXml> getResourceDefs() { return resourceDefs; }
	public void setResourceDefs(List<ResourceDefXml> resourceDefs) { this.resourceDefs = resourceDefs; }

	@XmlElement(name = "matrix")
	public List<MatrixXml> getMatrices() { return matrices; }
	public void setMatrices(List<MatrixXml> matrices) { this.matrices = matrices; }

	@XmlElement(name = "linear")
	public List<LinearXml> getLinears() { return linears; }
	public void setLinears(List<LinearXml> linears) { this.linears = linears; }
	
}
