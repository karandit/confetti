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
		"consecutiveIfSameDay", "mumberOfActivities", "activityId", "minDays", 
		"active", "comment"})
public class ConstraintMinDaysBetweenActivities extends TimeConstraint {
	
	private boolean consecutiveIfSameDay;
	private int numberOfActivities;
	private List<Long> activityId = new ArrayList<>();
	private int minDays;
	
	@XmlElement(name = "Consecutive_If_Same_Day")
	public boolean isConsecutiveIfSameDay() { return consecutiveIfSameDay; }
	public void setConsecutiveIfSameDay(boolean value) { this.consecutiveIfSameDay = value; }
	
	@XmlElement(name = "Number_of_Activities")
	public int getMumberOfActivities() { return numberOfActivities; }
	public void setMumberOfActivities(int value) { this.numberOfActivities = value; }
	
	@XmlElement(name = "Activity_Id")
	public List<Long> getActivityId() { return activityId; }

	public void setActivityId(List<Long> value) {
		this.activityId = value;
		this.numberOfActivities = value.size();
	}
	
	@XmlElement(name = "MinDays")
	public int getMinDays() { return minDays; }
	public void setMinDays(int value) { this.minDays = value; }
	
	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
