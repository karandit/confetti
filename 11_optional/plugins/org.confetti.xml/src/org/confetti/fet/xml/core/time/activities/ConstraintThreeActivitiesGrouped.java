package org.confetti.fet.xml.core.time.activities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.fet.xml.core.ConstraintXmlVisitor;
import org.confetti.fet.xml.core.time.TimeConstraint;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = { "weight", "firstActivityId", "secondActivityId", "thirdActivityId", "active", "comment" })
public class ConstraintThreeActivitiesGrouped extends TimeConstraint {
	@XmlElement(name = "First_Activity_Id") public int firstActivityId;
	@XmlElement(name = "Second_Activity_Id") public int secondActivityId;
	@XmlElement(name = "Third_Activity_Id") public int thirdActivityId;

	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
