package org.confetti.xml.core;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlType(name = "subgroup_type", propOrder = {"name", "nrOfStudents"})
public class SubgroupXml {

	private String name;
	private Integer nrOfStudents;
	
	@XmlElement(name = "Name")
	public String getName() 				{ return name; }
	public void setName(String name) 		{ this.name = name; }
	
	@XmlElement(name = "Number_of_Students")
	public Integer getNrOfStudents() 					{ return nrOfStudents; }
	public void setNrOfStudents(Integer nrOfStudents) 	{ this.nrOfStudents = nrOfStudents; }

}
