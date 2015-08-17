package org.confetti.xml.core.space.activities;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.xml.core.ConstraintXmlVisitor;
import org.confetti.xml.core.space.SpaceConstraint;

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
	@XmlElement(name = "Activity_Id") public List<Long> activityIds;
	@XmlElement(name = "Max_Number_of_Different_Rooms") public int maxNrOfDifferentRooms;
	
	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitSpace(this, param);
	}
}
