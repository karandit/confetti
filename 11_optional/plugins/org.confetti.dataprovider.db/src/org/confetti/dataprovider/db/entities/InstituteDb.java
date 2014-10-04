package org.confetti.dataprovider.db.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

/**
 * @author Gabor Bubla
 */
@Entity
@Table(name = "ttt_institute")
public class InstituteDb implements Serializable {

    private static final long serialVersionUID = 1L;
    
    // --------------- fields ------------------------------------------------------------------------------------------
	private Long id;
    private String name;
	private String version;
	private String comment;
	private List<DayDb> days = new ArrayList<>();
	private List<HourDb> hours = new ArrayList<>();
	private Set<SubjectDb> subjects = new HashSet<>();
	private Set<TeacherDb> teachers = new HashSet<>();
	private Set<StudentGroupDb> studentGroups = new HashSet<>();
	private Set<RoomDb> rooms = new HashSet<>();
	private Set<AssignmentDb> assignments = new HashSet<>();

	public InstituteDb() {
	}
	
	public InstituteDb(String instituteName, String comment, List<String> days, List<String> hours) {
        this.name = instituteName;
        this.version = "5.23.2";
        this.comment = comment;
        for (String day : days) {
            this.days.add(new DayDb(day, this));
        }
        for (String hour : hours) {
            this.hours.add(new HourDb(hour, this));
        }
    }

    // --------------- getters and setters -----------------------------------------------------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
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
    @OrderColumn(name = "hour_index")
    public List<HourDb> getHours()                                   { return hours; }
    public void setHours(List<HourDb> hours)                         { this.hours = hours; }
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "inst_fk")
    @OrderColumn(name = "day_index")
    public List<DayDb> getDays()                                     { return days; }
    public void setDays(List<DayDb> days)                            { this.days = days; }
    
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
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "inst_fk")
    public Set<AssignmentDb> getAssignments()                      { return assignments; }
    public void setAssignments(Set<AssignmentDb> assignments)      { this.assignments = assignments; }
    
}
