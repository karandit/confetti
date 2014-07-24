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
		"minHoursDaily", "students", "allowEmptyDays",
		"active", "comment"})
public class ConstraintStudentsSetMinHoursDaily extends TimeConstraint {
	@XmlElement(name = "Minimum_Hours_Daily") 	int minHoursDaily;
	@XmlElement(name = "Students") 	String students;
	@XmlElement(name = "Allow_Empty_Days") 	boolean allowEmptyDays;
}
