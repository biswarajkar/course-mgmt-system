/**
 * 
 */
package com.jga.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author dey
 *
 */
@Entity
@Table(name = "Course")
public class Course implements Serializable {

	private static final long serialVersionUID = -2592937593227686781L;

	/**
	 * Data Members
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "courseId", updatable = false, nullable = false)
	private int courseId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "identifier", nullable = false)
	private String identifier;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "createDate", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Column(name = "updateDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	@Column(name = "displayGridOrder")
	private Integer displayGridOrder;

	/**
	 * @return the courseId
	 */
	public int getCourseId() {
		return courseId;
	}

	/**
	 * @param courseId
	 *            the courseId to set
	 */
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier
	 *            the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate
	 *            the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the displayGridOrder
	 */
	public Integer getDisplayGridOrder() {
		return displayGridOrder;
	}

	/**
	 * @param displayGridOrder
	 *            the displayGridOrder to set
	 */
	public void setDisplayGridOrder(Integer displayGridOrder) {
		this.displayGridOrder = displayGridOrder;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
