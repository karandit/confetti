package org.confetti.tablix.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "info_type", propOrder = {"title", "author"} )
public class InfoXml {

	// --------------- fields ------------------------------------------------------------------------------------------
	private String title;
	private String author;

	// --------------- getters and setters -----------------------------------------------------------------------------
	@XmlElement(name = "title")
	public String getTitle() 										{ return title; }
	public void setTitle(String title) 								{ this.title = title; }

	@XmlElement(name = "author")
	public String getAuthor() 										{ return author; }
	public void setAuthor(String author) 							{ this.author = author; }

}
