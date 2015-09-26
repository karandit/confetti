package org.confetti.xml.header;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "fet")
public class InstituteHeaderXml {

	// --------------- fields ------------------------------------------------------------------------------------------
	private String version;
	
	// --------------- getters and setters -----------------------------------------------------------------------------
	@XmlAttribute(name = "version")
	public String getVersion() 										{ return version; }
	public void setVersion(String version) 							{ this.version = version; }

}
