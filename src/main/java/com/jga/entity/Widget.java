/**
 * 
 */
package com.jga.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * @author biswaraj
 *
 */
@Entity
@Table(name = "Widget")
@Inheritance(strategy = InheritanceType.JOINED)
public class Widget implements Serializable {

	private static final long serialVersionUID = 2342067425654448001L;

	/**
	 * Data Members
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "widgetId", updatable = false, nullable = false)
	private int widgetId;

	@Column(name = "name")
	private String name;

	@Column(name = "topPosition")
	private Float topPosition;

	@Column(name = "bottomPosition")
	private Float bottomPosition;

	@Column(name = "leftPosition")
	private Float leftPosition;

	@Column(name = "rightPosition")
	private Float rightPosition;

	@Column(name = "width")
	private Float width;

	@Column(name = "height")
	private Float height;

	@Column(name = "foregroundColor")
	private String foregroundColor;

	@Column(name = "backgroundColor")
	private String backgroundColor;

	@Column(name = "cssClass")
	private String cssClass;

	@Column(name = "cssStyle")
	private String cssStyle;

	@Column(name = "scrollable")
	private Boolean scrollable;

	@Column(name = "fitContents")
	private Boolean fitContents;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the widgetId
	 */
	public int getWidgetId() {
		return widgetId;
	}

	/**
	 * @param widgetId
	 *            the widgetId to set
	 */
	public void setWidgetId(int widgetId) {
		this.widgetId = widgetId;
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
	 * @return the topPosition
	 */
	public Float getTopPosition() {
		return topPosition;
	}

	/**
	 * @param topPosition
	 *            the topPosition to set
	 */
	public void setTopPosition(Float topPosition) {
		this.topPosition = topPosition;
	}

	/**
	 * @return the bottomPosition
	 */
	public Float getBottomPosition() {
		return bottomPosition;
	}

	/**
	 * @param bottomPosition
	 *            the bottomPosition to set
	 */
	public void setBottomPosition(Float bottomPosition) {
		this.bottomPosition = bottomPosition;
	}

	/**
	 * @return the leftPosition
	 */
	public Float getLeftPosition() {
		return leftPosition;
	}

	/**
	 * @param leftPosition
	 *            the leftPosition to set
	 */
	public void setLeftPosition(Float leftPosition) {
		this.leftPosition = leftPosition;
	}

	/**
	 * @return the rightPosition
	 */
	public Float getRightPosition() {
		return rightPosition;
	}

	/**
	 * @param rightPosition
	 *            the rightPosition to set
	 */
	public void setRightPosition(Float rightPosition) {
		this.rightPosition = rightPosition;
	}

	/**
	 * @return the width
	 */
	public Float getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(Float width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public Float getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(Float height) {
		this.height = height;
	}

	/**
	 * @return the foregroundColor
	 */
	public String getForegroundColor() {
		return foregroundColor;
	}

	/**
	 * @param foregroundColor
	 *            the foregroundColor to set
	 */
	public void setForegroundColor(String foregroundColor) {
		this.foregroundColor = foregroundColor;
	}

	/**
	 * @return the backgroundColor
	 */
	public String getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * @param backgroundColor
	 *            the backgroundColor to set
	 */
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	/**
	 * @return the cssClass
	 */
	public String getCssClass() {
		return cssClass;
	}

	/**
	 * @param cssClass
	 *            the cssClass to set
	 */
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	/**
	 * @return the cssStyle
	 */
	public String getCssStyle() {
		return cssStyle;
	}

	/**
	 * @param cssStyle
	 *            the cssStyle to set
	 */
	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
	}

	/**
	 * @return the scrollable
	 */
	public Boolean getScrollable() {
		return scrollable;
	}

	/**
	 * @param scrollable
	 *            the scrollable to set
	 */
	public void setScrollable(Boolean scrollable) {
		this.scrollable = scrollable;
	}

	/**
	 * @return the fitContents
	 */
	public Boolean getFitContents() {
		return fitContents;
	}

	/**
	 * @param fitContents
	 *            the fitContents to set
	 */
	public void setFitContents(Boolean fitContents) {
		this.fitContents = fitContents;
	}

}
