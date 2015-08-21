package org.confetti.xml.core.time.misc;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.xml.core.ConstraintXmlVisitor;
import org.confetti.xml.core.time.TimeConstraint;

/**
 * @author Bubla Gabor
 */
@XmlRootElement(name = "ConstraintBasicCompulsoryTime")
@XmlType(name = "basicCompulsoryTime_type", propOrder = {"weight", "active", "comment"})
public class ConstraintBasicCompulsoryTime extends TimeConstraint {
	
	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
