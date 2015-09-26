package org.confetti.xml.core;

import java.util.List;
import java.util.function.Function;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.confetti.core.ConstraintAttributes;
import org.confetti.dataprovider.xml.ConstraintBuilder;
import org.confetti.xml.core.space.SpaceConstraint;
import org.confetti.xml.core.time.TimeConstraint;
import org.confetti.xml.internal.DoubleWithoutFractionalAdapter;

/**
 * @author Bubla Gabor
 */
@XmlTransient
public abstract class BaseConstraintXml {
	
	private double weight = 100.0;
	private boolean active = true;
	private String comment = "";
	
	@XmlElement(name = "Weight_Percentage")
	@XmlJavaTypeAdapter(type = Double.class, value = DoubleWithoutFractionalAdapter.class)
	public Double getWeight() { return weight; }
	public void setWeight(Double weight) { this.weight = weight; }
	
	@XmlElement(name = "Active")
	public boolean isActive() { return active; }
	public void setActive(boolean active) { this.active = active; }
	
	@XmlElement(name = "Comments")
	public String getComment() { return comment; }
	public void setComment(String comment) { this.comment = comment; }

	public abstract <R, P> R accept(ConstraintXmlVisitor<R, P> visitor, P param);

	//------------------------ static methods --------------------------------------------------------------------------
	public static BaseConstraintXml newXmlConstraint(AbstractInstituteXml instXml, final String type, 
			ConstraintAttributes attrs, ConstraintSetter constraintSetter) {
		
		//create xml constraint depending on the type
		String shortType = type.substring(ConstraintBuilder.FET_CONSTRAINTS_NAMESPACE.length());
		BaseConstraintXml xmlConstr = shortType.startsWith("time")
			? newXmlConstraint(instXml.getTimeConstraints(), TimeConstraint::newTimeXmlConstraint, shortType)
			: newXmlConstraint(instXml.getSpaceConstraints(), SpaceConstraint::newSpaceXmlConstraint, shortType);
		
		//set the fields for the newly created xml constraint
		xmlConstr.accept(constraintSetter, attrs);
		return xmlConstr;
	}
			
	private static <T extends BaseConstraintXml> T newXmlConstraint(List<T> xmlCons, Function<String, T> fact, 
			String shortType) {
		T xmlConstr = fact.apply(shortType);
		xmlCons.add(xmlConstr);
		return xmlConstr;
	}
}
