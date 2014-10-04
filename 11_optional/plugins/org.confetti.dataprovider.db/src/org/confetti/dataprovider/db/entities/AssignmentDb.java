package org.confetti.dataprovider.db.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

/**
 * @author Gabor Bubla
 */
@Entity
@Table(name = "ttt_assignment")
public class AssignmentDb implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;
    private InstituteDb institute;
    private Set<SubjectDb> subjects = new HashSet<>();
    private Set<TeacherDb> teachers = new HashSet<>();
    private Set<StudentGroupDb> studentGroups = new HashSet<>();
    
    AssignmentDb() {
    }
    
    public AssignmentDb(InstituteDb institute, Iterable<SubjectDb> subjects, Iterable<TeacherDb> teachers, Iterable<StudentGroupDb> studentGroups) {
        this.institute = institute;
        for (SubjectDb subject : subjects) {
            this.subjects.add(subject);
            subject.getAssignments().add(this);
        }
        for (TeacherDb teacher : teachers) {
            this.teachers.add(teacher);
            teacher.getAssignments().add(this);
        }
        for (StudentGroupDb studentGroup : studentGroups) {
            this.studentGroups.add(studentGroup);
            studentGroup.getAssignments().add(this);
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    @ManyToOne
    @JoinColumn(name = "inst_fk")
    @ForeignKey(name = "fk_assignment_inst")
    public InstituteDb getInstitute() { return institute; }
    public void setInstitute(InstituteDb institute) { this.institute = institute; }

    @ManyToMany
    @JoinTable(name = "ttt_subject_x_assignment", 
        inverseJoinColumns = {@JoinColumn( name = "subj_id")},
        joinColumns = {@JoinColumn( name = "assi_id")}
    )
    public Set<SubjectDb> getSubjects() { return subjects; }
    public void setSubjects(Set<SubjectDb> subjects) { this.subjects = subjects; }

    @ManyToMany
    @JoinTable(name = "ttt_teacher_x_assignment", 
        inverseJoinColumns = {@JoinColumn( name = "teacher_id")},
        joinColumns = {@JoinColumn( name = "assi_id")}
    )
    public Set<TeacherDb> getTeachers() { return teachers; }
    public void setTeachers(Set<TeacherDb> teachers) { this.teachers = teachers; }

    @ManyToMany
    @JoinTable(name = "ttt_studentgroup_x_assignment", 
        inverseJoinColumns = {@JoinColumn( name = "studentgroup_id")},
        joinColumns = {@JoinColumn( name = "assi_id")}
    )
    public Set<StudentGroupDb> getStudentGroups() { return studentGroups; }
    public void setStudentGroups(Set<StudentGroupDb> studentGroups) { this.studentGroups = studentGroups; }
    
}
