package org.confetti.xml.core.time.activities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.xml.core.ConstraintXmlVisitor;
import org.confetti.xml.core.time.PreferredTimeXml;
import org.confetti.xml.core.time.TimeConstraint;

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
	@XmlElement(name = "Teacher_Name") 						public String teacherName;
	@XmlElement(name = "Students_Name") 					public String studentsName;
	@XmlElement(name = "Subject_Name") 						public String subjectName;
	@XmlElement(name = "Activity_Tag_Name") 				private String activityTagName;
	@XmlElement(name = "Number_of_Preferred_Time_Slots") 	private int nrOfPreferredTimeSlots;
	private List<PreferredTimeXml> preferredTimeSlots = new ArrayList<>();

	@XmlElement(name = "Preferred_Time_Slot") 		
	public List<PreferredTimeXml> getPreferredTimeSlots() {
		return preferredTimeSlots;
	}

	public void setPreferredTimeSlots(List<PreferredTimeXml> value) {
		this.preferredTimeSlots = value;
		this.nrOfPreferredTimeSlots = value.size();
	}

	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
