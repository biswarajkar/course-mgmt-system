/**
 * 
 */
package com.jga.entity;

import java.beans.Transient;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * @author dey
 *
 */
@Entity
@Table(name = "CourseRole")
@IdClass(CourseRolePK.class)
public class CourseRole implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    public CourseRolePK id;
	
	@Column(name = "role", nullable = false, insertable=false,updatable=false)
    protected int role;

    @Column(name = "courseId", nullable = false, insertable=false,updatable=false)
    protected int courseId;
    
    @Column(name = "personId", nullable = false, insertable=false,updatable=false)
    protected int personId;

	/**
	 * @return the role
	 */
	public int getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(int role) {
		this.role = role;
	}

	/**
	 * @return the courseId
	 */
	public int getCourseId() {
		return courseId;
	}

	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	/**
	 * @return the personId
	 */
	public int getPersonId() {
		return personId;
	}

	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(int personId) {
		this.personId = personId;
	}

	/**
	 * @return the id
	 */
	@Transient
	public CourseRolePK getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	@Transient
	public void setId(CourseRolePK id) {
		this.id = id;
	}
	
}
