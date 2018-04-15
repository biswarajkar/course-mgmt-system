/**
 * 
 */
package com.jga.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author dey
 *
 */
@Entity
@Table(name = "Faculty")
@PrimaryKeyJoinColumn(referencedColumnName = "personId")
public class Faculty extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Generated(GenerationTime.INSERT)
	@Column(name = "facultyId", insertable = false)
	private int facultyId;

	@Column(name = "createDate", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();
	
	@JsonIgnore
	@Transient
	private final String roleType = "faculty";
	
	/**
	 * @return the facultyId
	 */
	public int getFacultyId() {
		return facultyId;
	}

	/**
	 * @param facultyId
	 *            the facultyId to set
	 */
	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	/**
	 * @return the createDate
	 */
	@Override
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	@Override
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * @return the roleType
	 */
	@JsonProperty
	public String getRoleType() {
		return roleType;
	}

}
