package org.confetti.xml.core.time;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = { "weight", "maxDaysPerWeek", "active", "comment"})
public class ConstraintTeachersMaxDaysPerWeek extends TimeConstraint {
	@XmlElement(name = "Max_Days_Per_Week") private int maxDaysPerWeek;
}
