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
@XmlType(propOrder = {
		"weight", 
		"activityTag", "maxHoursContinuously", 
		"active", "comment"})
public class ConstraintStudentsActivityTagMaxHoursContinuously extends TimeConstraint {

	@XmlElement(name = "Activity_Tag") public String activityTag;
	@XmlElement(name = "Maximum_Hours_Continuously") public int maxHoursContinuously;

	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
