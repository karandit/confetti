package org.confetti.xml.core;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.transform;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Tag;
import org.confetti.core.Teacher;
import org.confetti.xml.internal.WSLongAdapter;

/**
 * @author Bubla Gabor
 */
@XmlType(name = "activity_type", propOrder = {"teachers", "subject", "activityTag", "students", 
		"duration", "totalDuration",
		"id", "activityGroupId", "nrOfStudents", "active", "comments"})
@XmlAccessorType(XmlAccessType.FIELD)
public class ActivityXml {
	
	@XmlElement(name = "Teacher")
	private List<TeacherRef> teachers;

	@XmlElement(name = "Subject")
	private SubjectRef subject;
	
	@XmlElement(name = "Activity_Tag")
	private List<String> activityTag;

	@XmlElement(name = "Students")
	private List<String> students;

	@XmlElement(name = "Duration")
	private Integer duration = 1;

	@XmlElement(name = "Total_Duration")
	private Integer totalDuration = 1;
	
	@XmlElement(name = "Id")
	@XmlJavaTypeAdapter(type = Long.class, value = WSLongAdapter.class)
	private Long id;

	@XmlElement(name = "Activity_Group_Id")
	private Integer activityGroupId = 0; //TODO: change to Long

	@XmlElement(name = "Number_Of_Students")
	private Integer nrOfStudents = null;
	
	@XmlElement(name = "Active")
	private boolean active = true;

	@XmlElement(name = "Comments")
	private String comments = "";
	
	ActivityXml() {
	}
	
	public ActivityXml(Long id, int duration, 
			Long groupId, int totalDuration,
			Subject subject, 
			Iterable<Teacher> teachers, 
			Iterable<StudentGroup> studentGroups,
			Iterable<Tag> tags) {
		
		this.id = id;
		this.duration = duration;
		this.activityGroupId = groupId.intValue(); 
		this.totalDuration = totalDuration;
		this.subject = new SubjectRef(subject.getName().getValue());
		this.teachers = transform(newArrayList(teachers), TeacherRef::new);
		this.students = transform(newArrayList(studentGroups), sG -> sG.getName().getValue());
		this.activityTag = transform(newArrayList(tags), tag -> tag.getName().getValue()); 
	}
	
	public Long getId() 									{ return id; }
	public void setId(Long id) 								{ this.id = id; }

	public List<TeacherRef> getTeachers() 					{ return teachers; }
	public void setTeachers(List<TeacherRef> teacherRefs) 	{ this.teachers = teacherRefs; }
	
	public SubjectRef getSubject() 							{ return subject; }
	public void setSubject(SubjectRef subject) 				{ this.subject = subject; }

	public List<String> getActivityTag() 					{ return activityTag; }
	public void setActivityTag(List<String> activityTag) 	{ this.activityTag = activityTag; }
	
	public Integer getDuration() 							{ return duration; }
	public void setDuration(Integer duration) 				{ this.duration = duration; }
	
	public Integer getTotalDuration() 						{ return totalDuration; }
	public void setTotalDuration(Integer totalDuration) 	{ this.totalDuration = totalDuration; }
	
	public boolean isActive() 								{ return active; }
	public void setActive(boolean active) 					{ this.active = active; }
	
	public String getComments() 							{ return comments; }
	public void setComments(String comments) 				{ this.comments = comments; }
	
	public List<String> getStudents() 						{ return students; }
	public void setStudents(List<String> students) 			{ this.students = students; }
	
	public Integer getActivityGroupId() 					{ return activityGroupId; }
	public void setActivityGroupId(Integer activityGroupId) { this.activityGroupId = activityGroupId; }
	
	/**
	 * FET doesn't write the nrOfStudents if the value is 0.
	 * @return the number of students
	 */
	public Integer getNrOfStudents() 						{ return (null == nrOfStudents) ? 0 : nrOfStudents; }
	public void setNrOfStudents(Integer value) 				{ this.nrOfStudents = (value == 0) ? null : value; }

}
