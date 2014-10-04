package org.confetti.dataprovider.db.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

/**
 * @author Gabor Bubla
 */
@Entity
@Table(name = "ttt_teacher")
public class TeacherDb extends AbstractEntityDb {

    private static final long serialVersionUID = 1L;
    
    private InstituteDb institute;
    private Set<AssignmentDb> assignments;
    
    TeacherDb() {
    }
    
    public TeacherDb(String name, InstituteDb institute) {
        setName(name);
        this.institute = institute;
    }

    @ManyToOne
    @JoinColumn(name = "inst_fk")
    @ForeignKey(name = "fk_teacher_inst")
    public InstituteDb getInstitute() { return institute; }
    public void setInstitute(InstituteDb institute) { this.institute = institute; }
    
    @ManyToMany(mappedBy = "teachers")
    public Set<AssignmentDb> getAssignments() { return assignments; }
    public void setAssignments(Set<AssignmentDb> assignments) { this.assignments = assignments; }
    
}
