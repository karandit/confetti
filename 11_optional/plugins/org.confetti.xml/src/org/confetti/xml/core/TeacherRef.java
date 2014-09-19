package org.confetti.xml.core;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import org.confetti.core.Teacher;

/**
 * @author Bubla Gabor
 */
@XmlType(name = "teacherref_type")
public class TeacherRef {
	// --------------- fields ------------------------------------------------------------------------------------------
	private String name;
	// --------------- constructors ------------------------------------------------------------------------------------
	TeacherRef() {
	}
	public TeacherRef(Teacher teacher) {
		this.name = teacher.getName().getValue();
	}
	// --------------- getters and setters -----------------------------------------------------------------------------
	@XmlValue
	public String getName() 				{ return name; }
	public void setName(String name) 		{ this.name = name; }
}
