package org.confetti.xml.core.time.activities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.xml.core.ConstraintXmlVisitor;
import org.confetti.xml.core.time.SelectedTimeXml;
import org.confetti.xml.core.time.TimeConstraint;

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
	@XmlElement(name = "Activity_Id") 							public List<Long> activityIds = new ArrayList<>();
	@XmlElement(name = "Number_of_Selected_Time_Slots") 		private int nrOfSelectedTimeSlots;
	@XmlElement(name = "Selected_Time_Slot") 					public List<SelectedTimeXml> selectedTimeSlots = new ArrayList<>();
	@XmlElement(name = "Max_Number_of_Occupied_Time_Slots") 	public int maxNrOfOccupiedTimeSlots;
	
	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}

}
