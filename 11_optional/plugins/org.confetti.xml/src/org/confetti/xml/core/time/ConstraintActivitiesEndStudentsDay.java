package org.confetti.xml.core.time;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlRootElement
@XmlType(propOrder = {
		"weight", 
		"teacherName", "studentsName", "subjectName", "activityTagName",
		"active", "comment"})
public class ConstraintActivitiesEndStudentsDay extends TimeConstraint {
	@XmlElement(name = "Teacher_Name") private String teacherName;
	@XmlElement(name = "Students_Name") private String studentsName;
	@XmlElement(name = "Subject_Name") private String subjectName;
	@XmlElement(name = "Activity_Tag_Name") private String activityTagName;
}