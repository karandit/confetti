package org.confetti.fet.xml.core.space.students;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.fet.xml.core.ConstraintXmlVisitor;
import org.confetti.fet.xml.core.space.SpaceConstraint;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = {"weight", "students", "maxBuildingChangesPerWeek", "active", "comment"})
public class ConstraintStudentsSetMaxBuildingChangesPerWeek extends SpaceConstraint {
	@XmlElement(name = "Students") public String students;
	@XmlElement(name = "Max_Building_Changes_Per_Week") public int maxBuildingChangesPerWeek;
	
	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitSpace(this, param);
	}
}