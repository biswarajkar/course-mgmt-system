package com.jga.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author biswaraj
 *
 */
@Entity
@Table(name = "HtmlWidget")
@PrimaryKeyJoinColumn(referencedColumnName = "widgetId")
public class HtmlWidget extends Widget {

	private static final long serialVersionUID = 4433705202573913995L;

	/**
	 * Data Members
	 */
	static final Integer MAX_CHARS = 500;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "htmlId", updatable = false, nullable = false)
	private int htmlId;

	@Column(name = "markupText")
	private String markupText;

	@Column(name = "maxCharacters")
	private Integer maxCharacters = MAX_CHARS;
	
	@JsonIgnore
	@Transient
	private final String widgetType = "htmlwidget";
	
	/**
	 * @return the htmlId
	 */
	public int getHtmlId() {
		return htmlId;
	}

	/**
	 * @param htmlId
	 *            the htmlId to set
	 */
	public void setHtmlId(int htmlId) {
		this.htmlId = htmlId;
	}

	/**
	 * @return the markupText
	 */
	public String getMarkupText() {
		return markupText;
	}

	/**
	 * @param markupText
	 *            the markupText to set
	 */
	public void setMarkupText(String markupText) {
		this.markupText = markupText;
	}

	/**
	 * @return the maxCharacters
	 */
	public Integer getMaxCharacters() {
		return maxCharacters;
	}

	/**
	 * @param maxCharacters
	 *            the maxCharacters to set
	 */
	public void setMaxCharacters(Integer maxCharacters) {
		this.maxCharacters = maxCharacters;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the widgetType
	 */
	@JsonProperty
	public String getWidgetType() {
		return widgetType;
	}

}
