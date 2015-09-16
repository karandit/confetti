package org.confetti.xml.core;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.transform;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.confetti.core.Assignment;
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
public class ActivityXml {
	
	private List<TeacherRef> teacherRefs;
	private SubjectRef subject;
	private List<String> activityTag;
	private List<String> students;
	private Integer duration = 1;
	private Integer totalDuration = 1;
	private Long id;
	private Integer activityGroupId = 0;
	private Integer nrOfStudents = 1;
	private boolean active = true;
	private String comments = "";
	
	ActivityXml() {
	}
	
	public ActivityXml(Long id, Assignment assg) {
        this(id,
        		assg.getDuration().getValue(),
        		assg.getSubject(), 
        		assg.getTeachers().getList(), 
        		assg.getStudentGroups().getList(),
        		assg.getTags().getList());
	}
	   
	public ActivityXml(Long id, int duration, Subject subject, Iterable<Teacher> teachers, Iterable<StudentGroup> studentGroups,
			Iterable<Tag> tags) {
		this.id = id;
		this.duration = duration;
		this.totalDuration = duration;
		this.subject = new SubjectRef(subject.getName().getValue());
		this.teacherRefs = transform(newArrayList(teachers), TeacherRef::new);
		this.students = transform(newArrayList(studentGroups), sG -> sG.getName().getValue());
		this.activityTag = transform(newArrayList(tags), tag -> tag.getName().getValue()); 
	}
	
//	@XmlID
	@XmlElement(name = "Id")
	@XmlJavaTypeAdapter(type = Long.class, value = WSLongAdapter.class)
	public Long getId() 									{ return id; }
	public void setId(Long id) 								{ this.id = id; }

//	@XmlIDREF
	@XmlElement(name = "Teacher")
	public List<TeacherRef> getTeachers() 					{ return teacherRefs; }
	public void setTeachers(List<TeacherRef> teacherRefs) 	{ this.teacherRefs = teacherRefs; }
	
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
