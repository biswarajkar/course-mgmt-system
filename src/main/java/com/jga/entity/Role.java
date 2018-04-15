/**
 * 
 */
package com.jga.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author dey
 *
 */
@Entity
@Table(name = "Role")
public class Role implements Serializable {

	private static final long serialVersionUID = -2592937593227686781L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
    protected Integer id;

	@Column(name = "name", nullable = false)
	protected String name;

    public Role() {
    	
    }
    
    public Role(Integer id, String name) {
    	this.id = id;
    	this.name = name;
    }
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
