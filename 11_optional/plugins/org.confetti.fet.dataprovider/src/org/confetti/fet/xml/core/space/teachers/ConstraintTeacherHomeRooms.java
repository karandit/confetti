package org.confetti.fet.xml.core.space.teachers;

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
		"teacher", "nrOfPreferredRooms", "rooms", 
		"active", "comment"})
public class ConstraintTeacherHomeRooms extends SpaceConstraint {
	@XmlElement(name = "Teacher") public String teacher;
	@XmlElement(name = "Number_of_Preferred_Rooms") private int nrOfPreferredRooms;
	private List<String> rooms = new ArrayList<>();
	
	@XmlElement(name = "Preferred_Room") 
	public List<String> getRooms() {
		return rooms;
	}

	public void setRooms(List<String> value) {
		this.rooms = value;
		this.nrOfPreferredRooms = value.size();
	}

	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitSpace(this, param);
	}
}
