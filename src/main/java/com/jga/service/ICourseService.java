/**
 * 
 */
package com.jga.service;

import java.util.Collection;

import com.jga.entity.Course;
import com.jga.model.CourseRole;

/**
 * @author dey
 *
 */

public interface ICourseService {
	public Course getCourseById(int id);

	public Collection<Course> getAllCourses();
	
	public Course addCourse(Course c);

	public Course updateCourse(Course course);
	
	public void deleteCourse(Course course);
	
	public Collection<CourseRole> getCourseRoleByPersonId(int id);

	public Course addCourse(Course course, int personId, String role);
}
