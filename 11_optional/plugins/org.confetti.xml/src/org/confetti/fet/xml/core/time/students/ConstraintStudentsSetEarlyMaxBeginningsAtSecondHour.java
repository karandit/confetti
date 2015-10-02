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
		"maxBeginningsAtSecondHour", "students",
		"active", "comment"})
public class ConstraintStudentsSetEarlyMaxBeginningsAtSecondHour extends TimeConstraint {
	@XmlElement(name = "Max_Beginnings_At_Second_Hour") 	public int maxBeginningsAtSecondHour;
	@XmlElement(name = "Students") 				public String students;
	
	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
