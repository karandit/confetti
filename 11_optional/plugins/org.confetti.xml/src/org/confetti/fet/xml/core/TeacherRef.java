package org.confetti.fet.xml.core;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

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
	public TeacherRef(String name) {
		this.name = name;
	}
	// --------------- getters and setters -----------------------------------------------------------------------------
	@XmlValue
	public String getName() 				{ return name; }
	public void setName(String name) 		{ this.name = name; }
}
