/**
 * 
 */
package com.jga.model;

import java.util.Collection;

import com.jga.entity.Course;
import com.jga.entity.Role;

/**
 * @author dey
 *
 */
public class CourseRole {
	private Course course;
	private Collection<Role> roles;
	private boolean isViewOnly;
	
	public CourseRole(Course course, Collection<Role> roles) {
		this.course = course;
		this.roles = roles;
		this.isViewOnly = roles.size() == 1 && "Viewer".equals(roles.iterator().next().getName());
	}

	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * @param course the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * @return the roles
	 */
	public Collection<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	/**
	 * @return the isViewOnly
	 */
	public boolean isViewOnly() {
		return isViewOnly;
	}

	/**
	 * @param isViewOnly the isViewOnly to set
	 */
	public void setViewOnly(boolean isViewOnly) {
		this.isViewOnly = isViewOnly;
	}
}
