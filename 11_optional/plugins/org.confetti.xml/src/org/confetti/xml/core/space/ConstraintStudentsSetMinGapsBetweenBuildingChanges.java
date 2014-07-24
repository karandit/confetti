package org.confetti.xml.core.space;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = {"weight", "students", "minGapsBetweenBuildingChanges", "active", "comment"})
public class ConstraintStudentsSetMinGapsBetweenBuildingChanges extends SpaceConstraint {
	@XmlElement(name = "Students") private String students;
	@XmlElement(name = "Min_Gaps_Between_Building_Changes") private int minGapsBetweenBuildingChanges;
}