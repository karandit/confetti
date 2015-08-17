package org.confetti.xml.core.time.teachers;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.xml.core.ConstraintXmlVisitor;
import org.confetti.xml.core.time.TimeConstraint;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = { "weight", "teacherName", "maxHoursDaily", "active", "comment"})
public class ConstraintTeacherMaxHoursDaily extends TimeConstraint {
	@XmlElement(name = "Teacher_Name") public String teacherName;
	@XmlElement(name = "Maximum_Hours_Daily") private int maxHoursDaily;

	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
