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
@Table(name = "ttt_room")
public class RoomDb extends AbstractEntityDb {

    private static final long serialVersionUID = 1L;
    
    private InstituteDb institute;
    
	RoomDb() {
    }
	
	public RoomDb(String name, InstituteDb institute) {
        setName(name);
        this.institute = institute;
    }

    @ManyToOne
    @JoinColumn(name = "inst_fk")
    @ForeignKey(name = "fk_room_inst")
    public InstituteDb getInstitute() { return institute; }
    public void setInstitute(InstituteDb institute) { this.institute = institute; }
    
}
