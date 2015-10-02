package org.confetti.fet.xml.core.time.teachers;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.fet.xml.core.ConstraintXmlVisitor;
import org.confetti.fet.xml.core.time.TimeConstraint;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = { "weight", "teacherName", "minDaysPerWeek", "active", "comment"})
public class ConstraintTeacherMinDaysPerWeek extends TimeConstraint {
	@XmlElement(name = "Teacher_Name") public String teacherName;
	@XmlElement(name = "Minimum_Days_Per_Week") public int minDaysPerWeek;

	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
