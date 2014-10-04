package org.confetti.dataprovider.db.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;


/**
 * @author Gabor Bubla
 */
@Entity
@Table(name = "ttt_hour")
public class HourDb {

    private Long id;
    private String name;
    private InstituteDb institute;
    
    HourDb() {
    }

    @Id
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @ManyToOne
    @JoinColumn(name = "inst_fk")
    @ForeignKey(name = "fk_hour_inst")
    public InstituteDb getInstitute() { return institute; }
    public void setInstitute(InstituteDb institute) { this.institute = institute; }
    
}
