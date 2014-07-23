package org.confetti.xml.core.space;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = {"weight", "maxBuildingChangesPerWeek", "active", "comment"})
public class ConstraintTeachersMaxBuildingChangesPerWeek extends SpaceConstraint {
	@XmlElement(name = "Max_Building_Changes_Per_Week") private int maxBuildingChangesPerWeek;
}
