package org.confetti.xml.core.time;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = { "weight", "maxGaps", "students", "active", "comment"})
public class ConstraintStudentsSetMaxGapsPerDay extends TimeConstraint {
	@XmlElement(name = "Max_Gaps") 		private int maxGaps;
	@XmlElement(name = "Students") 		private String students;
}
