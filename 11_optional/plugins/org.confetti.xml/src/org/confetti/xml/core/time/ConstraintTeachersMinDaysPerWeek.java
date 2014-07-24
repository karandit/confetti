package org.confetti.xml.core.time;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = { "weight", "minDaysPerWeek", "active", "comment"})
public class ConstraintTeachersMinDaysPerWeek extends TimeConstraint {
	@XmlElement(name = "Minimum_Days_Per_Week") private int minDaysPerWeek;
}