package org.confetti.xml.core;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.confetti.core.IActivityTag;
import org.confetti.core.IHour;
import org.confetti.core.IInstitute;
import org.confetti.core.IRoom;
import org.confetti.core.ISubject;
import org.confetti.core.ITeacher;

@XmlRootElement(name = "fet")
public class InstituteXml implements IInstitute {

	// --------------- fields ------------------------------------------------------------------------------------------
	private String name;
	private String version;
	private String comment;
	private List<IHour> hours;
	private List<ISubject> subjects;
	private List<ITeacher> teachers;
	private List<IRoom> rooms;
	private List<IActivityTag> activityTags;
	
	// --------------- getters and setters -----------------------------------------------------------------------------
	@XmlAttribute(name = "version")
	public String getVersion() 										{ return version; }
	public void setVersion(String version) 							{ this.version = version; }

	public List<IHour> getHours() 									{ return hours; }
	public void setHours(List<IHour> hours) 						{ this.hours = hours; }

	@Override
	@XmlElement(name = "Institution_Name")
	public String getName() 										{ return name; }
	public void setName(String name) 								{ this.name = name; }

	@Override	
	@XmlElement(name = "Comments")
	public String getComment() 										{ return comment; }
	public void setComment(String comment) 							{ this.comment = comment; }
	
	@Override
	@XmlElementWrapper(name = "Subjects_List")
	@XmlElement(name = "Subject", type = SubjectXml.class)
	public List<ISubject> getSubjects() 							{ return subjects; }
	public void setSubjects(List<ISubject> subjects) 				{ this.subjects = subjects; }
	
	@Override
	@XmlElementWrapper(name = "Teachers_List")
	@XmlElement(name = "Teacher", type = TeacherXml.class)
	public List<ITeacher> getTeachers() 							{ return teachers; }
	public void setTeachers(List<ITeacher> teachers) 				{ this.teachers = teachers; }
	
	@Override
	@XmlElementWrapper(name = "Rooms_List")
	@XmlElement(name = "Room", type = RoomXml.class)
	public List<IRoom> getRooms() 									{ return rooms; }
	public void setRooms(List<IRoom> rooms) 						{ this.rooms = rooms; }
	
	@Override
	@XmlElementWrapper(name = "Activity_Tags_List")
	@XmlElement(name = "Activity_Tag", type = ActivityTagXml.class)
	public List<IActivityTag> getActivityTags() 					{ return activityTags; }
	public void setActivityTags(List<IActivityTag> activityTags) 	{ this.activityTags = activityTags; }
		
}
