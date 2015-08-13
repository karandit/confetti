package org.confetti.xml.core.time;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.xml.core.ConstraintXmlVisitor;

/**
 * @author Bubla Gabor
 */
@XmlRootElement(name = "ConstraintActivityPreferredStartingTime")
@XmlType(name = "preferredStartingTime_type", 
	propOrder = {"weight", "activityId", "preferredDay", "preferredHour", "locked", "active", "comment"})
public class ConstraintActivityPreferredStartingTime extends TimeConstraint {

	private long activityId;
	private String preferredDay;
	private String preferredHour;
	private boolean locked;
	
	@XmlElement(name = "Activity_Id")
	public long getActivityId() 							{ return activityId; }
	public void setActivityId(long activityId) 				{ this.activityId = activityId; }
	
	@XmlElement(name = "Preferred_Day")
	public String getPreferredDay() 						{ return preferredDay; }
	public void setPreferredDay(String preferredDay) 		{ this.preferredDay = preferredDay; }
	
	@XmlElement(name = "Preferred_Hour")
	public String getPreferredHour() 						{ return preferredHour; }
	public void setPreferredHour(String preferredHour) 		{ this.preferredHour = preferredHour; }
	
	@XmlElement(name = "Permanently_Locked")
	public boolean isLocked() { return locked; }
	public void setLocked(boolean locked) { this.locked = locked; }
	
	@Override
	protected <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
