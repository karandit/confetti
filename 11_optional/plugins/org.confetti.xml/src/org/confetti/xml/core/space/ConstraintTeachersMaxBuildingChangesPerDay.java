package org.confetti.xml.core.space;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = {"weight", "maxBuildingChangesPerDay", "active", "comment"})
public class ConstraintTeachersMaxBuildingChangesPerDay extends SpaceConstraint {
	@XmlElement(name = "Max_Building_Changes_Per_Day") private int maxBuildingChangesPerDay;
}
