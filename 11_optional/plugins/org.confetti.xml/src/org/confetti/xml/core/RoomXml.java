package org.confetti.xml.core;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.core.IRoom;

@XmlType(name = "room_type")
public class RoomXml implements IRoom {

	private String name;
	private int capacity;
	
	@Override 
	@XmlElement(name = "Name")
	public String getName() 				{ return name; }
	public void setName(String name) 		{ this.name = name; }
	
	@Override 
	@XmlElement(name = "Capacity")
	public int getCapacity() 				{ return capacity; }
	public void setCapacity(int capacity) 	{ this.capacity = capacity; }

}
