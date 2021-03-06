package org.confetti.fet.xml.core.time.students;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.fet.xml.core.ConstraintXmlVisitor;
import org.confetti.fet.xml.core.time.BreakTimeXml;
import org.confetti.fet.xml.core.time.TimeConstraint;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = {
		"weight", 
		"studentsName", "nrOfNotAvailableTimes", "notAvailableTimes", 
		"active", "comment"})
public class ConstraintStudentsSetNotAvailableTimes extends TimeConstraint {
	@XmlElement(name = "Students") public String studentsName;
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
		return visitor.visitTime(this, param);
	}
}
