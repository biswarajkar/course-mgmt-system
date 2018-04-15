package com.jga.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author biswaraj
 *
 */
@Entity
@Table(name = "Theme")
public class Theme implements Serializable {

	private static final long serialVersionUID = -4938018165702280836L;

	/**
	 * Data Members
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "themeId", updatable = false, nullable = false)
	private Integer themeId;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "version")
	private String version;

	@Column(name = "createDate", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();

	@Column(name = "updateDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	@Column(name = "noOfUses")
	private Integer noOfUses = 0;

	@Column(name = "themeBackgroundImage")
	private String themeBackgroundImage;

	@Column(name = "stylesheetLink")
	private String stylesheetLink;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "theme")
	private List<Layout> layouts = new ArrayList<>();

	/**
	 * @return the themeId
	 */
	public Integer getThemeId() {
		return themeId;
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
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
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
	 * @return the noOfUses
	 */
	public Integer getNoOfUses() {
		return noOfUses;
	}

	/**
	 * @param noOfUses
	 *            the noOfUses to set
	 */
	public void setNoOfUses(Integer noOfUses) {
		this.noOfUses = noOfUses;
	}

	/**
	 * @return the themeBackgroundImage
	 */
	public String getThemeBackgroundImage() {
		return themeBackgroundImage;
	}

	/**
	 * @param themeBackgroundImage
	 *            the themeBackgroundImage to set
	 */
	public void setThemeBackgroundImage(String themeBackgroundImage) {
		this.themeBackgroundImage = themeBackgroundImage;
	}

	/**
	 * @return the stylesheetLink
	 */
	public String getStylesheetLink() {
		return stylesheetLink;
	}

	/**
	 * @param stylesheetLink
	 *            the stylesheetLink to set
	 */
	public void setStylesheetLink(String stylesheetLink) {
		this.stylesheetLink = stylesheetLink;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
