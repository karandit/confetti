package org.confetti.fet.engine.solution;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Gabor Bubla
 */
@XmlRootElement(name = "Activities_Timetable")
@XmlType(name = "solution_type")
public class SolutionXML {

	private List<ResultActivityXML> activities;

	@XmlElement(name = "Activity")
	public List<ResultActivityXML> getActivities() 					{ return activities; }
	public void setActivities(List<ResultActivityXML> activities) 	{ this.activities = activities; }
	
}
