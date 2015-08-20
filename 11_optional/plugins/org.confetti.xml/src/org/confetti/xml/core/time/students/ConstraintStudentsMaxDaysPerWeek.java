package org.confetti.xml.core.time.students;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.xml.core.ConstraintXmlVisitor;
import org.confetti.xml.core.time.TimeConstraint;

@XmlRootElement
@XmlType(propOrder = {
		"weight", 
		"maxDaysPerWeek", 
		"active", "comment"})
public class ConstraintStudentsMaxDaysPerWeek extends TimeConstraint {
	@XmlElement(name = "Max_Days_Per_Week") public int maxDaysPerWeek;

	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}

}
