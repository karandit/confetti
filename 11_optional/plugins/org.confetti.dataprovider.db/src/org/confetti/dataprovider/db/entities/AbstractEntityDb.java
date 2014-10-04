package org.confetti.dataprovider.db.entities;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Gabor Bubla
 */
@MappedSuperclass
public abstract class AbstractEntityDb implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
}
