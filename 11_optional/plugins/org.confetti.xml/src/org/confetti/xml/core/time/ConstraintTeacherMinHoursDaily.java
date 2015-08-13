package org.confetti.xml.core.time;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.xml.core.ConstraintXmlVisitor;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = { "weight", 
		"teacherName", "minimumHoursDaily", "allowEmptyDays", 
		"active", "comment"})
public class ConstraintTeacherMinHoursDaily extends TimeConstraint {
	@XmlElement(name = "Teacher_Name") 			private String teacherName;
	@XmlElement(name = "Minimum_Hours_Daily") 	private int minimumHoursDaily;
	@XmlElement(name = "Allow_Empty_Days") 		private boolean allowEmptyDays;

	@Override
	protected <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
