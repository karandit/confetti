package org.confetti.xml.core.space;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlRootElement(name = "ConstraintBasicCompulsorySpace")
@XmlType(name = "basicCompulsorySpace_type", propOrder = {"weight", "active", "comment"})
public class ConstraintBasicCompulsorySpace extends SpaceConstraint {

}
