package org.confetti.fet.xml.core.time.students;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.fet.xml.core.ConstraintXmlVisitor;
import org.confetti.fet.xml.core.time.TimeConstraint;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = {
		"weight", 
		"maxHoursContinuously", "students", "activityTag",
		"active", "comment"})
public class ConstraintStudentsSetActivityTagMaxHoursContinuously extends TimeConstraint {
	@XmlElement(name = "Maximum_Hours_Continuously") 	public int maxHoursContinuously;
	@XmlElement(name = "Students") 				public String students;
	@XmlElement(name = "Activity_Tag") 				public String activityTag;

	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
