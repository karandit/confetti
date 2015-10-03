package org.confetti.fet.xml.core.space.activities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.fet.xml.core.ConstraintXmlVisitor;
import org.confetti.fet.xml.core.space.SpaceConstraint;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = {
		"weight", 
		"nrOfActivities", "activityIds", "maxNrOfDifferentRooms", 
		"active", "comment"})
public class ConstraintActivitiesOccupyMaxDifferentRooms extends SpaceConstraint {
	@XmlElement(name = "Number_of_Activities") private int nrOfActivities;
	private List<Long> activityIds = new ArrayList<>();
	@XmlElement(name = "Max_Number_of_Different_Rooms") public int maxNrOfDifferentRooms;
	
	@XmlElement(name = "Activity_Id") 
	public List<Long> getActivityIds() {
		return activityIds;
	}

	public void setActivityIds(List<Long> value) {
		this.activityIds = value;
		this.nrOfActivities = value.size();
	}

	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitSpace(this, param);
	}
}
