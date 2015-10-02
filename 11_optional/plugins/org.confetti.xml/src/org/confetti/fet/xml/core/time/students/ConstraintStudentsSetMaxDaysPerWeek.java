package org.confetti.fet.xml.core.time.students;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.fet.xml.core.ConstraintXmlVisitor;
import org.confetti.fet.xml.core.time.TimeConstraint;

@XmlRootElement
@XmlType(propOrder = {
		"weight", 
		"students", "maxDaysPerWeek", 
		"active", "comment"})
public class ConstraintStudentsSetMaxDaysPerWeek extends TimeConstraint {
	@XmlElement(name = "Students") public String students;
	@XmlElement(name = "Max_Days_Per_Week") public int maxDaysPerWeek;

	@Override
	public <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitTime(this, param);
	}
}
