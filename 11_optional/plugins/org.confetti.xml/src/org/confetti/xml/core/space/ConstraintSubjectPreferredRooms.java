package org.confetti.xml.core.space;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = {
		"weight", 
		"subject", "nrOfPreferredRooms", "preferredRooms",
		"active", "comment"})
public class ConstraintSubjectPreferredRooms extends SpaceConstraint {
	@XmlElement(name = "Subject") private String subject;
	@XmlElement(name = "Number_of_Preferred_Rooms") private int nrOfPreferredRooms;
	@XmlElement(name = "Preferred_Room") private List<String> preferredRooms;
}
