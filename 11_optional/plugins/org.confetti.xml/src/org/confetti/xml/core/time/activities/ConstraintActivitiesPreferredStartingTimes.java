package org.confetti.xml.core.time.activities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.xml.core.ConstraintXmlVisitor;
import org.confetti.xml.core.time.PreferredStartingTimeXml;
import org.confetti.xml.core.time.TimeConstraint;

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
	@XmlElement(name = "Teacher_Name") 							public String teacherName;
	@XmlElement(name = "Students_Name") 						public String studentsName;
	@XmlElement(name = "Subject_Name") 							public String subjectName;
	@XmlElement(name = "Activity_Tag_Name") 					public String activityTagName;
	@XmlElement(name = "Number_of_Preferred_Starting_Times") 	private int nrOfPreferredStartingTimes;
	private List<PreferredStartingTimeXml> preferredStartingTimes = new ArrayList<>();

	@XmlElement(name = "Preferred_Starting_Time") 				
	public List<PreferredStartingTimeXml> getPreferredStartingTimes() {
		return preferredStartingTimes;
	}

	public void setPreferredStartingTimes(List<PreferredStartingTimeXml> value) {
		this.preferredStartingTimes = value;
		this.nrOfPreferredStartingTimes = value.size();
	}

	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
