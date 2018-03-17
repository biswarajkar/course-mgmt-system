/**
 * 
 */
package com.jga.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.jga.entity.Course;
import com.jga.repository.CourseRepository;

/**
 * @author dey
 *
 */
public class CourseService implements ICourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	public Course getCourseById(int id) {
		return courseRepository.findOne(id);
	}

	@Override
	public Collection<Course> getAllCourses() {
		final Collection<Course> courses = new ArrayList<>();
		courseRepository.findAll().iterator().forEachRemaining(courses::add);
		
		return courses;
	}

}
