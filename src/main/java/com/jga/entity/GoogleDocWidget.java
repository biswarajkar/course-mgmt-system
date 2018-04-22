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
@Table(name = "GoogleDocWidget")
@PrimaryKeyJoinColumn(referencedColumnName = "widgetId")
public class GoogleDocWidget extends Widget {

	private static final long serialVersionUID = -3150515093588410649L;

	/**
	 * Data Members
	 */
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "googleDocId", updatable = false, nullable = false)
	private int googleDocId;

	@Column(name = "url")
	private String url;

	@Column(name = "editable")
	private Boolean editable;

	@Column(name = "type")
	private String type;
	
	@JsonIgnore
	@Transient
	private final String widgetType = "googledocwidget";
	/**
	 * @return the googleDocId
	 */
	public int getGoogleDocId() {
		return googleDocId;
	}

	/**
	 * @param googleDocId
	 *            the googleDocId to set
	 */
	public void setGoogleDocId(int googleDocId) {
		this.googleDocId = googleDocId;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the editable
	 */
	public Boolean getEditable() {
		return editable;
	}

	/**
	 * @param editable
	 *            the editable to set
	 */
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
