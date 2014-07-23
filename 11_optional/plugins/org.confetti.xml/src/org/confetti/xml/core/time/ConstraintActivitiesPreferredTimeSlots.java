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
		"teacherName", "studentsName", "subjectName", "activityTagName", 
		"nrOfPreferredTimeSlots", "preferredTimeSlots",
		"active", "comment"})
public class ConstraintActivitiesPreferredTimeSlots extends TimeConstraint {
	@XmlElement(name = "Teacher_Name") 						private String teacherName;
	@XmlElement(name = "Students_Name") 					private String studentsName;
	@XmlElement(name = "Subject_Name") 						private String subjectName;
	@XmlElement(name = "Activity_Tag_Name") 				private String activityTagName;
	@XmlElement(name = "Number_of_Preferred_Time_Slots") 	private int nrOfPreferredTimeSlots;
	@XmlElement(name = "Preferred_Time_Slot") 				private List<PreferredTimeXml> preferredTimeSlots;
}
