package org.confetti.xml.core.time.activities;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.xml.core.ConstraintXmlVisitor;
import org.confetti.xml.core.time.PreferredTimeXml;
import org.confetti.xml.core.time.TimeConstraint;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = {
		"weight", 
		"activityId", "nrOfPreferredTimeSlots", "preferredTimeSlots",
		"active", "comment"})
public class ConstraintActivityPreferredTimeSlots extends TimeConstraint {
	@XmlElement(name = "Activity_Id") 						public long activityId;
	@XmlElement(name = "Number_of_Preferred_Time_Slots") 	private int nrOfPreferredTimeSlots;
	@XmlElement(name = "Preferred_Time_Slot") 				private List<PreferredTimeXml> preferredTimeSlots;

	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
