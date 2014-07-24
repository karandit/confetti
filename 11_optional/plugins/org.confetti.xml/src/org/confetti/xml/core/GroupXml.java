package org.confetti.xml.core;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlType(name = "group_type", propOrder = {"name", "nrOfStudents", "subgroups"})
public class GroupXml {

	private String name;
	private Integer nrOfStudents;
	private List<SubgroupXml> subgroups = new ArrayList<>();
	
	@XmlElement(name = "Name")
	public String getName() 				{ return name; }
	public void setName(String name) 		{ this.name = name; }
	
	@XmlElement(name = "Number_of_Students")
	public Integer getNrOfStudents() 					{ return nrOfStudents; }
	public void setNrOfStudents(Integer nrOfStudents) 	{ this.nrOfStudents = nrOfStudents; }

	@XmlElement(name = "Subgroup")
	public List<SubgroupXml> getSubgroups() 					{ return subgroups; }
	public void setSubgroups(List<SubgroupXml> subgroups) 		{ this.subgroups = subgroups; }

}
