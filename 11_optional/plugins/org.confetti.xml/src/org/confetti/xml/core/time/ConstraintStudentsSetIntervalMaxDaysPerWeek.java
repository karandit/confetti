package org.confetti.xml.core.time;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = {
		"weight", 
		"students", "intervalStartHour", "intervalEndHour", "maxDaysPerWeek", 
		"active", "comment"})
public class ConstraintStudentsSetIntervalMaxDaysPerWeek extends TimeConstraint {
	@XmlElement(name = "Students") private String students;
	@XmlElement(name = "Interval_Start_Hour") private String intervalStartHour;
	@XmlElement(name = "Interval_End_Hour") private String intervalEndHour;
	@XmlElement(name = "Max_Days_Per_Week") private int maxDaysPerWeek;
}
