package org.confetti.xml.core;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "room_type",
propOrder = {"name", "building", "capacity"})
public class RoomXml implements INameBean {

	private String name;
	private int capacity;
	
	private String building;
	
	RoomXml() {
	}
	
	public RoomXml(String name, int capacity) {
		this.name = name;
		this.capacity = capacity;
	}
	
	@XmlElement(name = "Name")
	@Override public String getName() 				{ return name; }
	@Override public void setName(String name) 		{ this.name = name; }
	
	@XmlElement(name = "Capacity")
	public int getCapacity() 				{ return capacity; }
	public void setCapacity(int capacity) 	{ this.capacity = capacity; }
	
	@XmlElement(name = "Building")
	public String getBuilding() 				{ return building; }
	public void setBuilding(String building)	{ this.building = building; }

}
