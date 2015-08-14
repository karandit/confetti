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
@XmlType(propOrder = {"weight", "students", "maxBuildingChangesPerDay", "active", "comment"})
public class ConstraintStudentsSetMaxBuildingChangesPerDay extends SpaceConstraint {
	@XmlElement(name = "Students") private String students;
	@XmlElement(name = "Max_Building_Changes_Per_Day") private int maxBuildingChangesPerDay;

	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitSpace(this, param);
	}
}