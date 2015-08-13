package org.confetti.xml.core.time;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.xml.core.ConstraintXmlVisitor;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = {"weight", "maxGaps", "active", "comment"})
public class ConstraintStudentsMaxGapsPerWeek extends TimeConstraint {
	
	private int maxGaps;

	@XmlElement(name = "Max_Gaps")
	public int getMaxGaps() { return maxGaps; }
	public void setMaxGaps(int maxGaps) { this.maxGaps = maxGaps; }
	
	@Override
	protected <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
