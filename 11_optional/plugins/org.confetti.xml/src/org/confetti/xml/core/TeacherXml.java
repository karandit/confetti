package org.confetti.xml.core;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.core.ITeacher;

@XmlType(name = "teacher_type")
public class TeacherXml implements ITeacher {

	private String name;

	@Override 
	@XmlElement(name = "Name")
	public String getName() 				{ return name; }
	public void setName(String name) 		{ this.name = name; }

}
