package org.confetti.tablix.xml;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "ttm")
@XmlType(name = "ttm_type", propOrder = {"info", "modules", "resources", "events"} )
public class TablixXml {
	// --------------- fields ------------------------------------------------------------------------------------------
	private String version;
	private InfoXml info;
	private ResourcesXml resources;
	private List<ModuleXml> modules = new LinkedList<>();
	private List<EventXml> events = new LinkedList<>();

	// --------------- getters and setters -----------------------------------------------------------------------------
	@XmlAttribute(name = "version")
	public String getVersion() 										{ return version; }
	public void setVersion(String version) 							{ this.version = version; }

	@XmlElement(name = "info")
	public InfoXml getInfo() 										{ return info; }
	public void setInfo(InfoXml info) 								{ this.info = info; }

	@XmlElementWrapper(name = "modules")
	@XmlElement(name = "module")
	public List<ModuleXml> getModules() 							{ return modules; }
	public void setModules(List<ModuleXml> modules)					{ this.modules = modules; }

	@XmlElement(name = "resources")
	public ResourcesXml getResources() 								{ return resources; }
	public void setResources(ResourcesXml resources) 				{ this.resources = resources; }

	@XmlElementWrapper(name = "events")
	@XmlElement(name = "event")
	public List<EventXml> getEvents() 								{ return events; }
	public void setEvents(List<EventXml> events) 					{ this.events = events; }
	

}
