/**
 * 
 */
package com.jga.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

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
	@Column(name = "adminId", insertable = false)
	private int adminId;

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

}
