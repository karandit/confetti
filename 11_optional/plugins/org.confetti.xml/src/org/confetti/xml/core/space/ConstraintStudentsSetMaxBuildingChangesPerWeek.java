package org.confetti.xml.core.space;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = {"weight", "students", "maxBuildingChangesPerWeek", "active", "comment"})
public class ConstraintStudentsSetMaxBuildingChangesPerWeek extends SpaceConstraint {
	@XmlElement(name = "Students") private String students;
	@XmlElement(name = "Max_Building_Changes_Per_Week") private int maxBuildingChangesPerWeek;
}