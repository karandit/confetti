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
@XmlType(propOrder = { "weight", "teacherName", "maxHoursContinuously", "active", "comment"})
public class ConstraintTeacherMaxHoursContinuously extends TimeConstraint {
	@XmlElement(name = "Teacher_Name") public String teacherName;
	@XmlElement(name = "Maximum_Hours_Continuously") private int maxHoursContinuously;

	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
