package org.confetti.xml.core.time.activities;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.xml.core.ConstraintXmlVisitor;
import org.confetti.xml.core.time.TimeConstraint;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = { "weight", 
		"nrOfActivities", "activityIds", 
		"active", "comment"})
public class ConstraintActivitiesNotOverlapping extends TimeConstraint {
	@XmlElement(name = "Number_of_Activities") 			private String nrOfActivities;
	@XmlElement(name = "Activity_Id") 					private List<Long> activityIds;
	
	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}	
}
