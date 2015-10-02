package org.confetti.fet.xml.core.time.activities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.fet.xml.core.ConstraintXmlVisitor;
import org.confetti.fet.xml.core.time.TimeConstraint;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = {
		"weight", 
		"nrOfActivities", "activityIds", "maxDays",
		"active", "comment"})
public class ConstraintMaxDaysBetweenActivities extends TimeConstraint {
	@XmlElement(name = "Number_of_Activities") private int nrOfActivities;
	private List<Long> activityIds = new ArrayList<>();

	@XmlElement(name = "Activity_Id") 
	public List<Long> getActivityIds() {
		return activityIds;
	}

	public void setActivityIds(List<Long> values) {
		this.activityIds = values;
		this.nrOfActivities = values.size();
	}


	@XmlElement(name = "MaxDays") public int maxDays;


	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}