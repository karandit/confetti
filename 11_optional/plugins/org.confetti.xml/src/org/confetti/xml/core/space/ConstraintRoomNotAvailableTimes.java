package org.confetti.xml.core.space;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.xml.core.ConstraintXmlVisitor;
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
	@XmlElement(name = "Room") private String room;
	@XmlElement(name = "Number_of_Not_Available_Times") private int nrOfNotAvailableTimes;
	@XmlElement(name = "Not_Available_Time") private List<BreakTimeXml> notAvailableTimes;
	
	@Override
	protected <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitSpace(this, param);
	}
}
