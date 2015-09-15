package org.confetti.xml.core.time.misc;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.xml.core.ConstraintXmlVisitor;
import org.confetti.xml.core.time.BreakTimeXml;
import org.confetti.xml.core.time.TimeConstraint;

/**
 * @author Bubla Gabor
 */
@XmlRootElement(name = "ConstraintBreakTimes")
@XmlType(name = "breakTimes_type", propOrder = {"weight", "nrBreakTimes", "breakTimes", "active", "comment"})
public class ConstraintBreakTimes extends TimeConstraint {
	
	@XmlElement(name = "Number_of_Break_Times") private int nrBreakTimes;
	private List<BreakTimeXml> breakTimes = new ArrayList<>(); 
	
	@XmlElement(name = "Break_Time")
	public List<BreakTimeXml> getBreakTimes() { return breakTimes; }

	public void setBreakTimes(List<BreakTimeXml> value) {
		this.breakTimes = value;
		this.nrBreakTimes = value.size();
	}
	
	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
