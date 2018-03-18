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
@Table(name = "VideoWidget")
@PrimaryKeyJoinColumn(referencedColumnName = "widgetId")
public class VideoWidget extends Widget {

	private static final long serialVersionUID = -4784006102250968762L;

	/**
	 * Data Members
	 */
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "videoId", updatable = false, nullable = false)
	private int videoId;

	@Column(name = "url")
	private String url;

	@Column(name = "youtubeId")
	private String youtubeId;

	@Column(name = "expandable")
	private Boolean expandable;

	/**
	 * @return the videoId
	 */
	public int getVideoId() {
		return videoId;
	}

	/**
	 * @param videoId
	 *            the videoId to set
	 */
	public void setVideoId(int videoId) {
		this.videoId = videoId;
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
	 * @return the youtubeId
	 */
	public String getYoutubeId() {
		return youtubeId;
	}

	/**
	 * @param youtubeId
	 *            the youtubeId to set
	 */
	public void setYoutubeId(String youtubeId) {
		this.youtubeId = youtubeId;
	}

	/**
	 * @return the expandable
	 */
	public Boolean getExpandable() {
		return expandable;
	}

	/**
	 * @param expandable
	 *            the expandable to set
	 */
	public void setExpandable(Boolean expandable) {
		this.expandable = expandable;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
