/**
 * 
 */
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.jga.controller.ThemeController;

/**
 * @author biswaraj
 *
 */
@Entity
@Table(name = "Layout")
public class Layout implements Serializable {

	private static final long serialVersionUID = 3537712068761959795L;

	/**
	 * Data Members
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "layoutId", updatable = false, nullable = false)
	private Integer layoutId;

	@OneToOne
	@JoinColumn(name = "courseId")
	private Course course;

	/**
	 * @param course
	 *            the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	@ManyToOne
	@JoinColumn(name = "themeId")
	private Theme theme;

	/**
	 * @param theme
	 *            the theme to set
	 */
	public void setTheme(Theme theme) {
		if (theme == null)
			this.theme = new ThemeController().getDefaultTheme().getBody();
		else
			this.theme = theme;
	}

	@Column(name = "name")
	private String name;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "createDate", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();

	@Column(name = "updateDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	@Column(name = "customBackground")
	private String customBackground;

	@Column(name = "stylesheetLink")
	private String stylesheetLink;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "layout", orphanRemoval = true)
	private List<Page> pages = new ArrayList<>();

	/**
	 * @return the layoutId
	 */
	public Integer getLayoutId() {
		return layoutId;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @return the customBackground
	 */
	public String getCustomBackground() {
		return customBackground;
	}

	/**
	 * @param customBackground
	 *            the customBackground to set
	 */
	public void setCustomBackground(String customBackground) {
		this.customBackground = customBackground;
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
	 * @return the pages
	 */
	public List<Page> getPages() {
		return pages;
	}

	/**
	 * @param pages
	 *            the pages to set
	 */
	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
