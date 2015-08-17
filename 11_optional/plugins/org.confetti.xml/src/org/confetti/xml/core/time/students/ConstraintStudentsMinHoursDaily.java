package org.confetti.xml.core.time.students;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.xml.core.ConstraintXmlVisitor;
import org.confetti.xml.core.time.TimeConstraint;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = { "weight", "minHoursDaily", "allowEmptyDays", "active", "comment"})
public class ConstraintStudentsMinHoursDaily extends TimeConstraint {
	@XmlElement(name = "Minimum_Hours_Daily") public int minHoursDaily;
	@XmlElement(name = "Allow_Empty_Days") public boolean allowEmptyDays;

	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
