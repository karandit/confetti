package org.confetti.xml.core.time.activities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.xml.core.ConstraintXmlVisitor;
import org.confetti.xml.core.time.PreferredStartingTimeXml;
import org.confetti.xml.core.time.TimeConstraint;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = {
		"weight", 
		"activityId", "nrOfPreferredStartingTimes", "preferredStartingTimes", 
		"active", "comment"})
public class ConstraintActivityPreferredStartingTimes extends TimeConstraint {

	@XmlElement(name = "Activity_Id") public long activityId;
	@XmlElement(name = "Number_of_Preferred_Starting_Times") private long nrOfPreferredStartingTimes;
	@XmlElement(name = "Preferred_Starting_Time") public List<PreferredStartingTimeXml> preferredStartingTimes = new ArrayList<>();

	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
