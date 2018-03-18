/**
 * 
 */
package com.jga.service;

import java.util.Collection;

import com.jga.entity.Course;

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
}
