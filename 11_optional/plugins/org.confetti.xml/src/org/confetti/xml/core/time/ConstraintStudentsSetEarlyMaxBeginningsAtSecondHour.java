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
		"maxBeginningsAtSecondHour", "students",
		"active", "comment"})
public class ConstraintStudentsSetEarlyMaxBeginningsAtSecondHour extends TimeConstraint {
	@XmlElement(name = "Max_Beginnings_At_Second_Hour") 	private int maxBeginningsAtSecondHour;
	@XmlElement(name = "Students") 				private String students;
	
	@Override
	protected <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
