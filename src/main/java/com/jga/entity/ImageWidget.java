package com.jga.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author biswaraj
 *
 */
@Entity
@Table(name = "ImageWidget")
@PrimaryKeyJoinColumn(referencedColumnName = "widgetId")
public class ImageWidget extends Widget {

	private static final long serialVersionUID = 1923699140530704516L;

	/**
	 * Data Members
	 */
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "imageId", updatable = false, nullable = false)
	private int imageId;

	@Column(name = "url")
	private String url;

	@Column(name = "verticalAlign")
	private String verticalAlign;

	@Column(name = "horizontalAlign")
	private String horizontalAlign;

	/**
	 * @return the imageId
	 */
	public int getImageId() {
		return imageId;
	}

	/**
	 * @param imageId
	 *            the imageId to set
	 */
	public void setImageId(int imageId) {
		this.imageId = imageId;
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
	 * @return the verticalAlign
	 */
	public String getVerticalAlign() {
		return verticalAlign;
	}

	/**
	 * @param verticalAlign
	 *            the verticalAlign to set
	 */
	public void setVerticalAlign(String verticalAlign) {
		this.verticalAlign = verticalAlign;
	}

	/**
	 * @return the horizontalAlign
	 */
	public String getHorizontalAlign() {
		return horizontalAlign;
	}

	/**
	 * @param horizontalAlign
	 *            the horizontalAlign to set
	 */
	public void setHorizontalAlign(String horizontalAlign) {
		this.horizontalAlign = horizontalAlign;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
