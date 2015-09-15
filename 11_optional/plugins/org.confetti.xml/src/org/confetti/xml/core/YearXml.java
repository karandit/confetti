package org.confetti.xml.core;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.core.StudentGroup;

/**
 * @author Bubla Gabor
 */
@XmlType(name = "year_type", propOrder = {"name", "nrOfStudents", "groups"})
public class YearXml implements INameBean {

	private String name;
	private Integer nrOfStudents = 0;
	private List<GroupXml> groups = new ArrayList<>();
	
	YearXml() {
	}
	
	public YearXml(StudentGroup sg) {
		this.name = sg.getName().getValue();
		this.nrOfStudents = sg.getNrOfStudents().getValue();
		for (StudentGroup child : sg.getChildren().getList()) {
			groups.add(new GroupXml(child.getName().getValue(), child.getNrOfStudents().getValue(),
					child.getChildren().getList()));
		}
	}
	
	@XmlElement(name = "Name")
	@Override public String getName() 					{ return name; }
	@Override public void setName(String name) 			{ this.name = name; }
	
	@XmlElement(name = "Number_of_Students")
	public Integer getNrOfStudents() 					{ return nrOfStudents; }
	public void setNrOfStudents(Integer nrOfStudents) 	{ this.nrOfStudents = nrOfStudents; }

	@XmlElement(name = "Group")
	public List<GroupXml> getGroups() 					{ return groups; }
	public void setGroups(List<GroupXml> groups) 		{ this.groups = groups; }

}
