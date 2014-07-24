package org.confetti.xml.core;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.confetti.xml.internal.WSLongAdapter;

/**
 * @author Bubla Gabor
 */
@XmlType(name = "activity_type", propOrder = {"teachers", "subject", "activityTag", "students", 
		"duration", "totalDuration",
		"id", "activityGroupId", "nrOfStudents", "active", "comments"})
public class ActivityXml {
	
	private List<TeacherRef> teacher;
	private SubjectRef subject;
	private List<String> activityTag;
	private List<String> students;
	private Integer duration;
	private Integer totalDuration;
	private Long id;
	private Integer activityGroupId;
	private Integer nrOfStudents;
	private boolean active;
	private String comments;
	
//	@XmlID
	@XmlElement(name = "Id")
	@XmlJavaTypeAdapter(type = Long.class, value = WSLongAdapter.class)
	public Long getId() 									{ return id; }
	public void setId(Long id) 								{ this.id = id; }

//	@XmlIDREF
	@XmlElement(name = "Teacher")
	public List<TeacherRef> getTeachers() 					{ return teacher; }
	public void setTeachers(List<TeacherRef> teacher) 		{ this.teacher = teacher; }
	
//	@XmlIDREF
	@XmlElement(name = "Subject")
	public SubjectRef getSubject() 							{ return subject; }
	public void setSubject(SubjectRef subject) 				{ this.subject = subject; }

	@XmlElement(name = "Activity_Tag")
	public List<String> getActivityTag() 					{ return activityTag; }
	public void setActivityTag(List<String> activityTag) 	{ this.activityTag = activityTag; }
	
	@XmlElement(name = "Duration")
	public Integer getDuration() 							{ return duration; }
	public void setDuration(Integer duration) 				{ this.duration = duration; }
	
	@XmlElement(name = "Total_Duration")
	public Integer getTotalDuration() 						{ return totalDuration; }
	public void setTotalDuration(Integer totalDuration) 	{ this.totalDuration = totalDuration; }
	
	@XmlElement(name = "Active")
	public boolean isActive() 								{ return active; }
	public void setActive(boolean active) 					{ this.active = active; }
	
	@XmlElement(name = "Comments")
	public String getComments() 							{ return comments; }
	public void setComments(String comments) 				{ this.comments = comments; }
	
	@XmlElement(name = "Students")
	public List<String> getStudents() 						{ return students; }
	public void setStudents(List<String> students) 			{ this.students = students; }
	
	@XmlElement(name = "Activity_Group_Id")
	public Integer getActivityGroupId() 					{ return activityGroupId; }
	public void setActivityGroupId(Integer activityGroupId) { this.activityGroupId = activityGroupId; }
	
	@XmlElement(name = "Number_Of_Students")
	public Integer getNrOfStudents() 						{ return nrOfStudents; }
	public void setNrOfStudents(Integer nrOfStudents) 		{ this.nrOfStudents = nrOfStudents; }

}
