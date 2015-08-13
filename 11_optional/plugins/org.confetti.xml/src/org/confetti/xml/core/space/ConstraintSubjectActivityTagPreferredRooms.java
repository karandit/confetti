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
		"subject", "activityTag", "nrOfPreferredRooms", "rooms", 
		"active", "comment"})
public class ConstraintSubjectActivityTagPreferredRooms extends SpaceConstraint {
	@XmlElement(name = "Subject") 				private String subject;
	@XmlElement(name = "Activity_Tag") 			private String activityTag;
	@XmlElement(name = "Number_of_Preferred_Rooms") private int nrOfPreferredRooms;
	@XmlElement(name = "Preferred_Room") 		private List<String> rooms;

	@Override
	protected <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitSpace(this, param);
	}
}
