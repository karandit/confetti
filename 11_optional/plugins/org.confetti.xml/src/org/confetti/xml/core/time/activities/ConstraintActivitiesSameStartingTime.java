package org.confetti.xml.core.time.activities;

import java.util.ArrayList;
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
@XmlType(propOrder = {
		"weight", 
		"subject", "nrOfActivities", "activityIds",
		"active", "comment"})
public class ConstraintActivitiesSameStartingTime extends TimeConstraint {
	@XmlElement(name = "Subject") private String subject;
	@XmlElement(name = "Number_of_Activities") private int nrOfActivities;
	@XmlElement(name = "Activity_Id") public List<Long> activityIds = new ArrayList<>();


	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
