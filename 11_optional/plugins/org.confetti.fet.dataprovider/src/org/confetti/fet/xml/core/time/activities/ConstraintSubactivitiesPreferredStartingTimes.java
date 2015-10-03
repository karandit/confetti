package org.confetti.fet.xml.core.time.activities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.fet.xml.core.ConstraintXmlVisitor;
import org.confetti.fet.xml.core.time.PreferredStartingTimeXml;
import org.confetti.fet.xml.core.time.TimeConstraint;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = {
		"weight", 
		"componentNumber", "teacherName", "studentsName", "subjectName", "activityTagName", 
		"nrOfPreferredStartingTimes", "preferredStartingTimes",
		"active", "comment"})
public class ConstraintSubactivitiesPreferredStartingTimes extends TimeConstraint {
	@XmlElement(name = "Component_Number") 						public int componentNumber;
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

	public void setPreferredStartingTimes(List<PreferredStartingTimeXml> values) {
		this.preferredStartingTimes = values;
		this.nrOfPreferredStartingTimes = values.size();
	}

	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
