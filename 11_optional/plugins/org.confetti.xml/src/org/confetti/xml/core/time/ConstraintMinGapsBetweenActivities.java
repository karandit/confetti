package org.confetti.xml.core.time;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.xml.core.ConstraintXmlVisitor;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = {
		"weight", 
		"nrOfActivities", "activityIds", "minGaps",
		"active", "comment"})
public class ConstraintMinGapsBetweenActivities extends TimeConstraint {

	@XmlElement(name = "Number_of_Activities") private int nrOfActivities;
	@XmlElement(name = "Activity_Id") private List<Long> activityIds;
	@XmlElement(name = "MinGaps") private int minGaps;
	

	@Override
	protected <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
