package org.confetti.xml.core.time;

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
		"maxHoursContinuously", "students", "activityTag",
		"active", "comment"})
public class ConstraintStudentsSetActivityTagMaxHoursContinuously extends TimeConstraint {
	@XmlElement(name = "Maximum_Hours_Continuously") 	private int maxHoursContinuously;
	@XmlElement(name = "Students") 				private String students;
	@XmlElement(name = "Activity_Tag") 				private String activityTag;

	@Override
	protected <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
