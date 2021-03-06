package org.confetti.fet.xml.core.time.activities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.fet.xml.core.ConstraintXmlVisitor;
import org.confetti.fet.xml.core.time.SelectedTimeXml;
import org.confetti.fet.xml.core.time.TimeConstraint;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = {
		"weight", 
		"nrActivities", "activityIds", "nrOfSelectedTimeSlots", "selectedTimeSlots", "maxNrOfOccupiedTimeSlots", 
		"active", "comment"})
public class ConstraintActivitiesOccupyMaxTimeSlotsFromSelection extends TimeConstraint {
	@XmlElement(name = "Number_of_Activities") 					private int nrActivities;
	private List<Long> activityIds = new ArrayList<>();
	@XmlElement(name = "Number_of_Selected_Time_Slots") 		private int nrOfSelectedTimeSlots;
	private List<SelectedTimeXml> selectedTimeSlots = new ArrayList<>();
	@XmlElement(name = "Max_Number_of_Occupied_Time_Slots") 	public int maxNrOfOccupiedTimeSlots;
	
	@XmlElement(name = "Activity_Id") 							
	public List<Long> getActivityIds() {
		return activityIds;
	}

	public void setActivityIds(List<Long> value) {
		this.activityIds = value;
		this.nrActivities = value.size();
	}

	@XmlElement(name = "Selected_Time_Slot") 					
	public List<SelectedTimeXml> getSelectedTimeSlots() {
		return selectedTimeSlots;
	}

	public void setSelectedTimeSlots(List<SelectedTimeXml> value) {
		this.selectedTimeSlots = value;
		this.nrOfSelectedTimeSlots = value.size();
	}

	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}

}
