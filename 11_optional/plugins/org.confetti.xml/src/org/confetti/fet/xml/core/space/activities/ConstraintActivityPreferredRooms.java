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
		"activityId", "nrOfPreferredRooms", "preferredRooms",
		"active", "comment"})
public class ConstraintActivityPreferredRooms extends SpaceConstraint {
	@XmlElement(name = "Activity_Id") public long activityId;
	@XmlElement(name = "Number_of_Preferred_Rooms") private int nrOfPreferredRooms;
	private List<String> preferredRooms = new ArrayList<>();
	
	@XmlElement(name = "Preferred_Room") 
	public List<String> getPreferredRooms() {
		return preferredRooms;
	}

	public void setPreferredRooms(List<String> value) {
		this.preferredRooms = value;
		this.nrOfPreferredRooms = value.size();
	}

	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitSpace(this, param);
	}
}
