package org.confetti.xml.core.space;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = {"weight", "minGapsBetweenBuildingChanges", "active", "comment"})
public class ConstraintTeachersMinGapsBetweenBuildingChanges extends SpaceConstraint {
	@XmlElement(name = "Min_Gaps_Between_Building_Changes") private int minGapsBetweenBuildingChanges;
}