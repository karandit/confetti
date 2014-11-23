package org.confetti.dataprovider.db.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

import org.confetti.core.Assignment;
import org.confetti.core.DataProvider;
import org.confetti.core.Day;
import org.confetti.core.Hour;
import org.confetti.core.Room;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;

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

	InstituteDb() {
	}
	
	public InstituteDb(DataProvider dp) {
        this.name = dp.getName().getValue();
        this.version = "5.23.2";
        //TODO dp.getComment().getValue()
        this.comment = "todo";
        for (Day day : dp.getDays().getList()) {
            this.days.add(new DayDb(day.getName().getValue(), this));
        }
        for (Hour hour : dp.getHours().getList()) {
            this.hours.add(new HourDb(hour.getName().getValue(), this));
        }
        for (Subject subj : dp.getSubjects().getList()) {
            this.subjects.add(new SubjectDb(subj.getName().getValue(), this));
        }
        for (Teacher teacher : dp.getTeachers().getList()) {
            this.teachers.add(new TeacherDb(teacher.getName().getValue(), this));
        }
        for (StudentGroup sg : dp.getStudentGroups().getList()) {
            StudentGroupDb sgDb = new StudentGroupDb(sg.getName().getValue(), this);
            this.studentGroups.add(sgDb);
            //TODO read as the children of the StudentGroups
        }
        for (Room room : dp.getRooms().getList()) {
            this.rooms.add(new RoomDb(room.getName().getValue(), this));
        }
        
        Map<String, StudentGroupDb> allStdGroups = collectStudentGroups(studentGroups);
        for (Assignment ass : dp.getAssignments().getList()) {
            SubjectDb subjDb = findByName(this.subjects.iterator(), ass.getSubject().getName().getValue());
            List<TeacherDb> teachersDb = new LinkedList<>();
            List<StudentGroupDb> sGsDb = new LinkedList<>();
            if (ass.getStudentGroups() != null) {
                for (StudentGroup stGroup : ass.getStudentGroups().getList()) {
                    sGsDb.add(allStdGroups.get(stGroup.getName().getValue()));
                }
            }
            if (ass.getTeachers() != null) {
                for (Teacher teacher : ass.getTeachers().getList()) {
                    teachersDb.add(findByName(this.teachers.iterator(), teacher.getName().getValue()));
                }
            }
            this.assignments.add(new AssignmentDb(this, Arrays.asList(subjDb), teachersDb, sGsDb));
        }
    }

	private Map<String, StudentGroupDb> collectStudentGroups(Set<StudentGroupDb> items) {
        Map<String, StudentGroupDb> res = new HashMap<>();
        for (StudentGroupDb sg : items) {
            res.put(sg.getName(), sg);
            //TODO implement children for StudentGroupDb
//            res.putAll(collectStudentGroups(sg.getChildren().getList()));
        }
        return res;
    }
	
	private static <T extends AbstractEntityDb> T findByName(Iterator<T> items, String name) {
        while (items.hasNext()) {
            T item = (T) items.next();
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
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
