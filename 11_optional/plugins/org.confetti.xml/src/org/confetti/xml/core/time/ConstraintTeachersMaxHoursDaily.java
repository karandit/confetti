package org.confetti.xml.core.time;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = { "weight", "maxHoursDaily", "active", "comment" })
public class ConstraintTeachersMaxHoursDaily extends TimeConstraint {
	@XmlElement(name = "Maximum_Hours_Daily") private int maxHoursDaily;
}
