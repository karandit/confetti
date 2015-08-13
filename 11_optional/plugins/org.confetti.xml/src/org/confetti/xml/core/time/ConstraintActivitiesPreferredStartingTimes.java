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
		"teacherName", "studentsName", "subjectName", "activityTagName", 
		"nrOfPreferredStartingTimes", "preferredStartingTimes",
		"active", "comment"})
public class ConstraintActivitiesPreferredStartingTimes extends TimeConstraint {
	@XmlElement(name = "Teacher_Name") 							private String teacherName;
	@XmlElement(name = "Students_Name") 						private String studentsName;
	@XmlElement(name = "Subject_Name") 							private String subjectName;
	@XmlElement(name = "Activity_Tag_Name") 					private String activityTagName;
	@XmlElement(name = "Number_of_Preferred_Starting_Times") 	private int nrOfPreferredStartingTimes;
	@XmlElement(name = "Preferred_Starting_Time") 				private List<PreferredStartingTimeXml> preferredStartingTimes;

	@Override
	protected <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
