/**
 * 
 */
package com.jga.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 * @author dey
 *
 */
@Entity
@Table(name = "Student")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(referencedColumnName = "personId")
public class Student extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	@Generated(GenerationTime.INSERT)
	@Column(name = "studentId", insertable=false)
	private Integer studentId;
	
	@Column(name = "createDate", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();
	

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the studentId
	 */
	public Integer getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

}
