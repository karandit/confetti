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
		"maxHoursContinuously", "students",
		"active", "comment"})
public class ConstraintStudentsSetMaxHoursContinuously extends TimeConstraint {
	@XmlElement(name = "Maximum_Hours_Continuously") 	private int maxHoursContinuously;
	@XmlElement(name = "Students") 				private String students;

	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
