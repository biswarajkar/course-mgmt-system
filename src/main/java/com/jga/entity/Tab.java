package com.jga.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Tab")
public class Tab implements Serializable {

	private static final long serialVersionUID = -7233601591525732841L;

	/**
	 * Data Members
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tabId", updatable = false, nullable = false)
	private Integer tabId;

	@Column(name = "name")
	private String name;

	@Column(name = "horizontalOrder")
	private Integer horizontalOrder;

	@ManyToOne
	@JoinColumn(name = "pageId")
	private Page page;

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(Page page) {
		this.page = page;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "TabWidget", joinColumns = @JoinColumn(name = "tabId"), inverseJoinColumns = @JoinColumn(name = "widgetId"))
	private List<Widget> widgets = new ArrayList<>();

	/**
	 * @return the widgets
	 */
	public List<Widget> getWidgets() {
		return widgets;
	}

	/**
	 * @param widgets
	 *            the widgets to set
	 */
	public void setWidgets(List<Widget> widgets) {
		this.widgets = widgets;
	}
	//
	// /**
	// * @param widget
	// */
	// public void addWidget(Widget widget) {
	//
	// if (!widgets.contains(widget)) {
	// widgets.add(widget);
	// if (!widget.getTabs().contains(this))
	// widget.getTabs().add(this);
	// }
	// }
	//
	// /**
	// * @param widget
	// */
	// public void removeWidget(Widget widget) {
	//
	// if (widgets.contains(widget)) {
	// widgets.remove(widget);
	// if (widget.getTabs().contains(this))
	// widget.getTabs().remove(this);
	// }
	// }

	/**
	 * @return the tabId
	 */
	public Integer getTabId() {
		return tabId;
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
	 * @return the horizontalOrder
	 */
	public Integer getHorizontalOrder() {
		return horizontalOrder;
	}

	/**
	 * @param horizontalOrder
	 *            the horizontalOrder to set
	 */
	public void setHorizontalOrder(Integer horizontalOrder) {
		this.horizontalOrder = horizontalOrder;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
