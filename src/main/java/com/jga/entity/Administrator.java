/**
 * 
 */
package com.jga.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
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
@Table(name = "Administrator")
@PrimaryKeyJoinColumn(referencedColumnName = "personId")
public class Administrator extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Generated(GenerationTime.INSERT)
	@Column(name = "adminId", insertable=false)
	private int adminId;
	
	@JsonIgnore
	@Transient
	private final String roleType = "administrator";
	/**
	 * @return the adminId
	 */
	public int getAdminId() {
		return adminId;
	}

	/**
	 * @param adminId
	 *            the adminId to set
	 */
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	/**
	 * @return the roleType
	 */
	@JsonProperty
	public String getRoleType() {
		return roleType;
	}
}
