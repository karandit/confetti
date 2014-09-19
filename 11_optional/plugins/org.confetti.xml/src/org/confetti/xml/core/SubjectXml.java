package org.confetti.xml.core;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "subject_type")
public class SubjectXml {

	private String name;
	
	SubjectXml() {
	}
	
	public SubjectXml(String name) {
		this.name = name;
	}

//	@XmlID
	@XmlElement(name = "Name")
	public String getName() 				{ return name; }
	public void setName(String name) 		{ this.name = name; }

}
