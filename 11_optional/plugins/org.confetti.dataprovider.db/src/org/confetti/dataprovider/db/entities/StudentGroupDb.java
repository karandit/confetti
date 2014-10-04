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
@Table(name = "ttt_studentgroup")
public class StudentGroupDb extends AbstractEntityDb {

	private InstituteDb institute;
    private Set<AssignmentDb> assignments;
	
    StudentGroupDb() {
    }
    
    @ManyToOne
    @JoinColumn(name = "inst_fk")
    @ForeignKey(name = "fk_studentgroup_inst")
    public InstituteDb getInstitute() { return institute; }
    public void setInstitute(InstituteDb institute) { this.institute = institute; }
    
    @ManyToMany(mappedBy = "studentGroups")
    public Set<AssignmentDb> getAssignments() { return assignments; }
    public void setAssignments(Set<AssignmentDb> assignments) { this.assignments = assignments; }
    
}
