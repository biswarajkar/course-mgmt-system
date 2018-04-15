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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author biswaraj
 *
 */
@Entity
@Table(name = "Page")
public class Page implements Serializable {

	private static final long serialVersionUID = -1504124147364585796L;

	/**
	 * Data Members
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pageId", updatable = false, nullable = false)
	private Integer pageId;

	@Column(name = "name")
	private String name;

	@Column(name = "tooltipDescription")
	private String tooltipDescription;

	@Column(name = "createDate", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();

	@Column(name = "updateDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	@Column(name = "verticalOrder")
	private Integer verticalOrder;

	@ManyToOne
	@JoinColumn(name = "layoutId")
	private Layout layout;

	/**
	 * @param layout
	 *            the layout to set
	 */
	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "page", orphanRemoval = true)
	private List<Tab> tabs = new ArrayList<>();

	/**
	 * @return the pageId
	 */
	public Integer getPageId() {
		return pageId;
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
	 * @return the tooltipDescription
	 */
	public String getTooltipDescription() {
		return tooltipDescription;
	}

	/**
	 * @param tooltipDescription
	 *            the tooltipDescription to set
	 */
	public void setTooltipDescription(String tooltipDescription) {
		this.tooltipDescription = tooltipDescription;
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
	 * @return the verticalOrder
	 */
	public Integer getVerticalOrder() {
		return verticalOrder;
	}

	/**
	 * @param verticalOrder
	 *            the verticalOrder to set
	 */
	public void setVerticalOrder(Integer verticalOrder) {
		this.verticalOrder = verticalOrder;
	}

	/**
	 * @return the tabs
	 */
	public List<Tab> getTabs() {
		return tabs;
	}

	/**
	 * @param tabs
	 *            the tabs to set
	 */
	public void setTabs(List<Tab> tabs) {
		this.tabs = tabs;
	}

	/**
	 * @param tab
	 */
	public void addTab(Tab tab) {
		if (!tabs.contains(tab)) {
			tabs.add(tab);
		}
	}

	/**
	 * @param tab
	 */
	public void removeTab(Tab tab) {
		if (tabs.contains(tab)) {
			tabs.remove(tab);
		}
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}