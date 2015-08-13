package org.confetti.xml.core.space;

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
		"activityTag", "nrOfPreferredRooms", "preferredRooms", 
		"active", "comment"})
public class ConstraintActivityTagPreferredRooms extends SpaceConstraint {
	@XmlElement(name = "Activity_Tag") private String activityTag;
	@XmlElement(name = "Number_of_Preferred_Rooms") private int nrOfPreferredRooms;
	@XmlElement(name = "Preferred_Room") private List<String> preferredRooms;
	
	@Override
	protected <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitSpace(this, param);
	}
}
