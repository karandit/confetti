package org.confetti.xml.core;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.confetti.core.IInstitute;

@XmlRootElement(name = "fet")
public class InstituteXml implements IInstitute {

	private String name;
	private String version;
	private String comment;

	@XmlAttribute(name = "version")
	public String getVersion() 				{ return version; }
	public void setVersion(String version) 	{ this.version = version; }

	@Override
	@XmlElement(name = "Institution_Name")
	public String getName() 				{ return name; }
	public void setName(String name) 		{ this.name = name; }

	@XmlElement(name = "Comments")
	public String getComment() 				{ return comment; }
	public void setComment(String comment) 	{ this.comment = comment; }
		
}
