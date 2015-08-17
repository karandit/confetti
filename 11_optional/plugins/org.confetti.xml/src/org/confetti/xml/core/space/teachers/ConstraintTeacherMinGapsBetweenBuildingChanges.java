package org.confetti.xml.core.space.teachers;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.xml.core.ConstraintXmlVisitor;
import org.confetti.xml.core.space.SpaceConstraint;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = {"weight", "teacher", "minGapsBetweenBuildingChanges", "active", "comment"})
public class ConstraintTeacherMinGapsBetweenBuildingChanges extends SpaceConstraint {
	@XmlElement(name = "Teacher") public String teacher;
	@XmlElement(name = "Min_Gaps_Between_Building_Changes") private int minGapsBetweenBuildingChanges;
	
	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitSpace(this, param);
	}
}
