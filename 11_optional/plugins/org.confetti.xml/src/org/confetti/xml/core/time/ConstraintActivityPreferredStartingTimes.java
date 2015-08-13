package org.confetti.xml.core.time;

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
		"activityId", "nrOfPreferredStartingTimes", "preferredStartingTimes", 
		"active", "comment"})
public class ConstraintActivityPreferredStartingTimes extends TimeConstraint {

	@XmlElement(name = "Activity_Id") private long activityId;
	@XmlElement(name = "Number_of_Preferred_Starting_Times") private long nrOfPreferredStartingTimes;
	@XmlElement(name = "Preferred_Starting_Time") private List<PreferredStartingTimeXml> preferredStartingTimes;

	@Override
	protected <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
