package org.confetti.dataprovider.db.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Gabor Bubla
 */
@Entity
@Table(name = "ttt_institute")
public class InstituteDb {

	// --------------- fields ------------------------------------------------------------------------------------------
	private Long id;
    private String name;
	private String version;
	private String comment;
	private Set<DayDb> days;
	private Set<HourDb> hours;
	private Set<SubjectDb> subjects;
	private Set<TeacherDb> teachers;
	private Set<StudentGroupDb> studentGroups;
	private Set<RoomDb> rooms;
	private Set<AssignmentDb> assignments;

	public InstituteDb() {
	}
	
	// --------------- getters and setters -----------------------------------------------------------------------------
	@Id
//	@GeneratedValue()
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	public String getName()                                         { return name; }
	public void setName(String name)                                { this.name = name; }
	
    public String getVersion()                                      { return version; }
    public void setVersion(String version)                          { this.version = version; }

    public String getComment()                                      { return comment; }
    public void setComment(String comment)                          { this.comment = comment; }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "inst_fk")
    public Set<HourDb> getHours()                                   { return hours; }
    public void setHours(Set<HourDb> hours)                         { this.hours = hours; }
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "inst_fk")
    public Set<DayDb> getDays()                                     { return days; }
    public void setDays(Set<DayDb> days)                            { this.days = days; }
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "inst_fk")
    public Set<SubjectDb> getSubjects()                            { return subjects; }
    public void setSubjects(Set<SubjectDb> subjects)               { this.subjects = subjects; }
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "inst_fk")
    public Set<TeacherDb> getTeachers()                            { return teachers; }
    public void setTeachers(Set<TeacherDb> teachers)               { this.teachers = teachers; }
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "inst_fk")
    public Set<StudentGroupDb> getStudentGroups()                   { return studentGroups; }
    public void setStudentGroups(Set<StudentGroupDb> sG)			{ this.studentGroups = sG; }
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "inst_fk")
    public Set<RoomDb> getRooms()                                  { return rooms; }
    public void setRooms(Set<RoomDb> rooms)                        { this.rooms = rooms; }
//    
//    public Set<AssignmentDb> getAssignments()                      { return assignments; }
//    public void setAssignments(Set<AssignmentDb> assignments)      { this.assignments = assignments; }
    
}
