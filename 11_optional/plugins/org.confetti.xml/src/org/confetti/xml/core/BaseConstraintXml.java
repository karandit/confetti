package org.confetti.xml.core;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Bubla Gabor
 */
@XmlTransient
public abstract class BaseConstraintXml {
	
	private double weight = 100.0;
	private boolean active = true;
	private String comment = "";
	
	@XmlElement(name = "Weight_Percentage")
	public double getWeight() { return weight; }
	public void setWeight(double weight) { this.weight = weight; }
	
	@XmlElement(name = "Active")
	public boolean isActive() { return active; }
	public void setActive(boolean active) { this.active = active; }
	
	@XmlElement(name = "Comments")
	public String getComment() { return comment; }
	public void setComment(String comment) { this.comment = comment; }

	public abstract <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param);

}
