package org.confetti.xml.core.space.students;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.xml.core.ConstraintXmlVisitor;
import org.confetti.xml.core.space.SpaceConstraint;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = {"weight", "maxBuildingChangesPerDay", "active", "comment"})
public class ConstraintStudentsMaxBuildingChangesPerDay extends SpaceConstraint {
	@XmlElement(name = "Max_Building_Changes_Per_Day") public int maxBuildingChangesPerDay;

	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitSpace(this, param);
	}
}
