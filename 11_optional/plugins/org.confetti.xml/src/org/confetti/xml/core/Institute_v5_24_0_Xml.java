package org.confetti.xml.core;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "fet")
@XmlType(name = "institute_type", 
		 propOrder = {"name", "comment", "days", "hours", "years", 
				 	"teachers", "subjects", "activityTags", "activities", "buildings", "rooms", 
				 	"timeConstraints", "spaceConstraints", "generationOptions"}
)
public class Institute_v5_24_0_Xml extends AbstractInstituteXml {

	// --------------- fields ------------------------------------------------------------------------------------------
	private Hours_v5_24_0_Xml hours;
	private Days_v5_24_0_Xml days;
	private List<GenerationOptionXml> generationOptions = new LinkedList<>();

	// --------------- constructors ------------------------------------------------------------------------------------
	
	Institute_v5_24_0_Xml() {
	}
	
	public Institute_v5_24_0_Xml(String name, String version, String comment) {
		super(name, version, comment);
	}
	
	// --------------- getters and setters -----------------------------------------------------------------------------
	@XmlElement(name = "Hours_List")
	public Hours_v5_24_0_Xml getHours() 									{ return hours; }
	public void setHours(Hours_v5_24_0_Xml hours) 							{ this.hours = hours; }
	
	@XmlElement(name = "Days_List")
	public Days_v5_24_0_Xml getDays() 										{ return days; }
	public void setDays(Days_v5_24_0_Xml days) 								{ this.days = days; }

	@XmlElementWrapper(name = "Timetable_Generation_Options_List")
	@XmlElement(name = "GroupActivitiesInInitialOrder")
	public List<GenerationOptionXml> getGenerationOptions() { return generationOptions; }
	public void setGenerationOptions(List<GenerationOptionXml> values) { this.generationOptions = values; }


}
