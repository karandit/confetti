package org.confetti.xml.core.time;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = { "weight", 
		"activityTagName", "maxHoursContinuously", 
		"active", "comment"})
public class ConstraintTeachersActivityTagMaxHoursContinuously extends TimeConstraint {
	@XmlElement(name = "Activity_Tag_Name") 			private String activityTagName;
	@XmlElement(name = "Maximum_Hours_Continuously") 	private int maxHoursContinuously;
	
}
