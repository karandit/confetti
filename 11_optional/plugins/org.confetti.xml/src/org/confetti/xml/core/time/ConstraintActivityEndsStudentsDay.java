package org.confetti.xml.core.time;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = {
		"weight", 
		"activityId",
		"active", "comment"})
public class ConstraintActivityEndsStudentsDay extends TimeConstraint {
	@XmlElement(name = "Activity_Id") 	int activityId;
}
