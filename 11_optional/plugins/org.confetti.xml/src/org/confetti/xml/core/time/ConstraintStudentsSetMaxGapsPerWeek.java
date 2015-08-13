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
		"maxGaps", "students",
		"active", "comment"})
public class ConstraintStudentsSetMaxGapsPerWeek extends TimeConstraint {
	@XmlElement(name = "Max_Gaps") 				private int maxGaps;
	@XmlElement(name = "Students") 				private String students;
	
	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
