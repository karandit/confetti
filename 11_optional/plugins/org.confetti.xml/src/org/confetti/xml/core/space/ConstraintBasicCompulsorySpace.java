package org.confetti.xml.core.space;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.confetti.xml.core.ConstraintXmlVisitor;

/**
 * @author Bubla Gabor
 */
@XmlRootElement(name = "ConstraintBasicCompulsorySpace")
@XmlType(name = "basicCompulsorySpace_type", propOrder = {"weight", "active", "comment"})
public class ConstraintBasicCompulsorySpace extends SpaceConstraint {

	@Override
	protected <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param) {
		return visitor.visitSpace(this, param);
	}

}
