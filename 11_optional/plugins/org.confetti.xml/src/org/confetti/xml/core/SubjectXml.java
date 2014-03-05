package org.confetti.xml.core;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.core.ISubject;

@XmlType(name = "subject_type")
public class SubjectXml implements ISubject {

	private String name;

	@Override 
	@XmlElement(name = "Name")
	public String getName() 				{ return name; }
	public void setName(String name) 		{ this.name = name; }

}
