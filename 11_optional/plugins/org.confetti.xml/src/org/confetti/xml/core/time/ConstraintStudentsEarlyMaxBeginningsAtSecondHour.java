package org.confetti.xml.core.time;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

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
	
}
