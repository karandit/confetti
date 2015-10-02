package org.confetti.fet.xml.core;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "teacher_type")
public class TeacherXml implements INameBean {

	private String name;
	
	TeacherXml() {
	}
	
	public TeacherXml(String name) {
		this.name = name;
	}

//	@XmlID
	@XmlElement(name = "Name")
	@Override public String getName() 				{ return name; }
	@Override public void setName(String name) 		{ this.name = name; }

}
