package org.confetti.fet.xml.core;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "generationoption_type",
		propOrder = {
		"nrOfActivities", "activityIds",
		"active", "comment"}
		)
public class GenerationOptionXml {

	private boolean active = true;
	private String comment = "";
	@XmlElement(name = "Number_of_Activities") 	private int nrOfActivities;
	private List<String> activityIds = new ArrayList<>();
	
	@XmlElement(name = "Active")
	public boolean isActive() { return active; }
	public void setActive(boolean active) { this.active = active; }
	
	@XmlElement(name = "Comments")
	public String getComment() { return comment; }
	public void setComment(String comment) { this.comment = comment; }

	@XmlElement(name = "Activity_Id") 		
	public List<String> getActivityIds() {
		return activityIds;
	}

	public void setActivityIds(List<String> value) {
		this.activityIds = value;
		this.nrOfActivities = value.size();
	}

}
