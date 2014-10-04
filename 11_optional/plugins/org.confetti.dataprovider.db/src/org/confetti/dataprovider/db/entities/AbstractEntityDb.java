package org.confetti.dataprovider.db.entities;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Gabor Bubla
 */
@MappedSuperclass
public abstract class AbstractEntityDb {

	private Long id;
    private String name;

    @Id
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
}
