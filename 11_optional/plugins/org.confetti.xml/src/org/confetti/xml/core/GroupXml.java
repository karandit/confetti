package org.confetti.xml.core;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.core.StudentGroup;

/**
 * @author Bubla Gabor
 */
@XmlType(name = "group_type", propOrder = {"name", "nrOfStudents", "subgroups"})
public class GroupXml {

	private String name;
	private Integer nrOfStudents = 1;
	private List<SubgroupXml> subgroups = new ArrayList<>();
	
	GroupXml() {
	}
	
	public GroupXml(String name, Iterable<StudentGroup> children) {
		this.name = name;
		for (StudentGroup child : children) {
			subgroups.add(new SubgroupXml(child.getName().getValue()));
		}
	}
	
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
