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
		"componentNumber", "teacherName", "studentsName", "subjectName", "activityTagName", 
		"nrOfPreferredTimeSlots", "preferredTimeSlots",
		"active", "comment"})
public class ConstraintSubactivitiesPreferredTimeSlots extends TimeConstraint {
	@XmlElement(name = "Component_Number") 					private int componentNumber;
	@XmlElement(name = "Teacher_Name") 						private String teacherName;
	@XmlElement(name = "Students_Name") 					private String studentsName;
	@XmlElement(name = "Subject_Name") 						private String subjectName;
	@XmlElement(name = "Activity_Tag_Name") 				private String activityTagName;
	@XmlElement(name = "Number_of_Preferred_Time_Slots") 	private int nrOfPreferredTimeSlots;
	@XmlElement(name = "Preferred_Time_Slot") 				private List<PreferredTimeXml> preferredTimeSlots;

	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
