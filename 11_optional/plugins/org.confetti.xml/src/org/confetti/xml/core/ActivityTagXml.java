package org.confetti.xml.core;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.core.IActivityTag;

@XmlType(name = "activitytag_type")
public class ActivityTagXml implements IActivityTag {

	private String name;
	
	@Override 
	@XmlElement(name = "Name")
	public String getName() 				{ return name; }
	public void setName(String name) 		{ this.name = name; }

}
