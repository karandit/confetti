package org.confetti.dataprovider.db.entities;

import java.util.Set;

import javax.persistence.Entity;
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
public class AssignmentDb {

    private Long id;
    private InstituteDb institute;
    private Set<SubjectDb> subjects;
    private Set<TeacherDb> teachers;
    private Set<StudentGroupDb> studentGroups;
    
    AssignmentDb() {
    }

    @Id
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
