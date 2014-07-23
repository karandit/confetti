package org.confetti.xml.core.time;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = { "weight", "minHoursDaily", "allowEmptyDays", "active", "comment" })
public class ConstraintTeachersMinHoursDaily extends TimeConstraint {
	@XmlElement(name = "Minimum_Hours_Daily") private int minHoursDaily;
	@XmlElement(name = "Allow_Empty_Days") private boolean allowEmptyDays;
}
