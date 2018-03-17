package com.jga.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author biswaraj
 *
 */
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

	/**
	 * @return the googleDocId
	 */
	public int getGoogleDocId() {
		return googleDocId;
	}

	/**
	 * @param googleDocId the googleDocId to set
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
	 * @param url the url to set
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
	 * @param editable the editable to set
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
	 * @param type the type to set
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

}
