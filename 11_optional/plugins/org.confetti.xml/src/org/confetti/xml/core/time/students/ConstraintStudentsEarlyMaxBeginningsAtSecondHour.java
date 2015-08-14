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
@XmlType(propOrder = {"weight", "maxBeginningsAtSecondHour", "active", "comment"})
public class ConstraintStudentsEarlyMaxBeginningsAtSecondHour extends TimeConstraint {
	
	private int maxBeginAt2ndHour;

	@XmlElement(name = "Max_Beginnings_At_Second_Hour")
	public int getMaxBeginningsAtSecondHour() 			{ return maxBeginAt2ndHour; }
	public void setMaxBeginningsAtSecondHour(int v) 	{ this.maxBeginAt2ndHour = v; }
	
	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
