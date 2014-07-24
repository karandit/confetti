package org.confetti.xml.core;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlType(name = "year_type", propOrder = {"name", "nrOfStudents", "groups"})
public class YearXml {

	private String name;
	private Integer nrOfStudents;
	private List<GroupXml> groups = new ArrayList<>();
	
	@XmlElement(name = "Name")
	public String getName() 							{ return name; }
	public void setName(String name) 					{ this.name = name; }
	
	@XmlElement(name = "Number_of_Students")
	public Integer getNrOfStudents() 					{ return nrOfStudents; }
	public void setNrOfStudents(Integer nrOfStudents) 	{ this.nrOfStudents = nrOfStudents; }

	@XmlElement(name = "Group")
	public List<GroupXml> getGroups() 					{ return groups; }
	public void setGroups(List<GroupXml> groups) 		{ this.groups = groups; }

}
