package org.confetti.xml.core.time;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = { "weight", "maxGaps", "active", "comment"})
public class ConstraintTeachersMaxGapsPerWeek extends TimeConstraint {
	@XmlElement(name = "Max_Gaps") private int maxGaps;
}
