package org.confetti.xml.core.time;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.xml.core.ConstraintXmlVisitor;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = {
		"weight", 
		"nrActivities", "activityIds", "nrOfSelectedTimeSlots", "selectedTimeSlots", "maxNrOfSimultaneousActivities", 
		"active", "comment"})
public class ConstraintActivitiesMaxSimultaneousInSelectedTimeSlots extends TimeConstraint {
	@XmlElement(name = "Number_of_Activities") 					private int nrActivities;
	@XmlElement(name = "Activity_Id") 							private List<Long> activityIds;
	@XmlElement(name = "Number_of_Selected_Time_Slots") 		private int nrOfSelectedTimeSlots;
	@XmlElement(name = "Selected_Time_Slot") 					private List<SelectedTimeXml> selectedTimeSlots;
	@XmlElement(name = "Max_Number_of_Simultaneous_Activities") private int maxNrOfSimultaneousActivities;

	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}

}