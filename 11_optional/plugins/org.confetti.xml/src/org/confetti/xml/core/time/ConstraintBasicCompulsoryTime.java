package org.confetti.xml.core.time;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.core.ConstraintAttributes;

/**
 * @author Bubla Gabor
 */
@XmlRootElement(name = "ConstraintBasicCompulsoryTime")
@XmlType(name = "basicCompulsoryTime_type", propOrder = {"weight", "active", "comment"})
public class ConstraintBasicCompulsoryTime extends TimeConstraint {
	
	ConstraintBasicCompulsoryTime() {
	}
	
	public ConstraintBasicCompulsoryTime(ConstraintAttributes attrs) {
		setWeight(attrs.asDouble("weight-percentage"));
		setActive(attrs.asBoolean("active"));
	}
	
}
