package org.confetti.xml.core.space;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = { "weight", "students", "room", "active", "comment"})
public class ConstraintStudentsSetHomeRoom extends SpaceConstraint {
	@XmlElement(name = "Students") private String students;
	@XmlElement(name = "Room") private String room;
}
