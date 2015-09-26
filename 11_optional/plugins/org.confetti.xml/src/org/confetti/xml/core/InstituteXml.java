package org.confetti.xml.core;

import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "fet")
@XmlType(name = "institute_type", 
		 propOrder = {"name", "comment", "hours", "days", "years", 
				 	"teachers", "subjects", "activityTags", "activities", "buildings", "rooms", 
				 	"timeConstraints", "spaceConstraints"}
)
public class InstituteXml extends AbstractInstituteXml {

	// --------------- fields ------------------------------------------------------------------------------------------
	private HoursXml hours;
	private DaysXml days;

	// --------------- constructors ------------------------------------------------------------------------------------
	InstituteXml() {
	}
	
	public InstituteXml(String name, String version, String comment) {
		super(name, version, comment);
	}
	
	// --------------- getters and setters -----------------------------------------------------------------------------
	@XmlElement(name = "Hours_List")
	public HoursXml getHours() 										{ return hours; }
	public void setHours(HoursXml hours) 							{ this.hours = hours; }
	
	@XmlElement(name = "Days_List")
	public DaysXml getDays() 										{ return days; }
	public void setDays(DaysXml days) 								{ this.days = days; }

	@Override
	@XmlTransient
	public List<String> getDayNames() {
		return getDays().getDays().stream().map(x -> x.getName()).collect(Collectors.toList());
	}

	@Override
	@XmlTransient
	public List<String> getHourNames() {
		return getHours().getHours().stream().map(x -> x.getName()).collect(Collectors.toList());
	}

}
