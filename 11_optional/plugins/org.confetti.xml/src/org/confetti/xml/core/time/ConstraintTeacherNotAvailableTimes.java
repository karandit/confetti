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
		"teacher", "nrOfNotAvailableTimes", "notAvailableTimes",  
		"active", "comment"})
public class ConstraintTeacherNotAvailableTimes extends TimeConstraint {
	
	private String teacher;
	private int nrOfNotAvailableTimes;
	private List<BreakTimeXml> notAvailableTimes;
	
	@XmlElement(name = "Teacher")
	public String getTeacher() { return teacher; }
	public void setTeacher(String value) { this.teacher = value; }
	
	@XmlElement(name = "Number_of_Not_Available_Times")
	public int getNrOfNotAvailableTimes() { return nrOfNotAvailableTimes; }
	public void setNrOfNotAvailableTimes(int value) { this.nrOfNotAvailableTimes = value; }
	
	@XmlElement(name = "Not_Available_Time")
	public List<BreakTimeXml> getNotAvailableTimes() { return notAvailableTimes; }
	public void setNotAvailableTimes(List<BreakTimeXml> value) { this.notAvailableTimes = value; }

	@Override
	protected <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
