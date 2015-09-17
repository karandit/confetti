package org.confetti.fet.engine.solution;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Gabor Bubla
 */
@XmlType(name = "activity_type", propOrder = {"id", "day", "hour", "room"}) 
public class ResultActivityXML {

	private String id;
	private String day;
	private String hour;
	private String room;
	
	@XmlElement(name = "Id")
	public String getId() 				{ return id; }
	public void setId(String id) 		{ this.id = id; }
	
	@XmlElement(name = "Day")
	public String getDay()				{ return day; }
	public void setDay(String day) 		{ this.day = day; }
	
	@XmlElement(name = "Hour")
	public String getHour() 			{ return hour; }
	public void setHour(String hour) 	{ this.hour = hour; }
	
	@XmlElement(name = "Room")
	public String getRoom() 			{ return room; }
	public void setRoom(String room) 	{ this.room = room; }

}
