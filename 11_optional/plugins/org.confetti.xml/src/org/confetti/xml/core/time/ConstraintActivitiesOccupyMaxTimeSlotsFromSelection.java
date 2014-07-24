package org.confetti.xml.core.time;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

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
	@XmlElement(name = "Activity_Id") 							private List<Long> activityIds;
	@XmlElement(name = "Number_of_Selected_Time_Slots") 		private int nrOfSelectedTimeSlots;
	@XmlElement(name = "Selected_Time_Slot") 					private List<SelectedTimeXml> selectedTimeSlots;
	@XmlElement(name = "Max_Number_of_Occupied_Time_Slots") 	private int maxNrOfOccupiedTimeSlots;

}
