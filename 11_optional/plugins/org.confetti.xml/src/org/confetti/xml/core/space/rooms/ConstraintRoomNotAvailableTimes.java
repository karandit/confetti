package org.confetti.xml.core.space.rooms;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.xml.core.ConstraintXmlVisitor;
import org.confetti.xml.core.space.SpaceConstraint;
import org.confetti.xml.core.time.BreakTimeXml;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = {
		"weight", 
		"room", "nrOfNotAvailableTimes", "notAvailableTimes", 
		"active", "comment"})
public class ConstraintRoomNotAvailableTimes extends SpaceConstraint {
	@XmlElement(name = "Room") public String room;
	@XmlElement(name = "Number_of_Not_Available_Times") private int nrOfNotAvailableTimes;
	private List<BreakTimeXml> notAvailableTimes = new ArrayList<>();
	
	@XmlElement(name = "Not_Available_Time") 
	public List<BreakTimeXml> getNotAvailableTimes() {
		return notAvailableTimes;
	}

	public void setNotAvailableTimes(List<BreakTimeXml> value) {
		this.notAvailableTimes = value;
		this.nrOfNotAvailableTimes = value.size();
	}

	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitSpace(this, param);
	}
}
