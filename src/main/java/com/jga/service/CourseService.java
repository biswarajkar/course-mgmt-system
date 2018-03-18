/**
 * 
 */
package com.jga.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jga.entity.Course;
import com.jga.repository.CourseRepository;

/**
 * @author dey
 *
 */
@Service
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

	@Override
	public Course addCourse(Course c) {
		return courseRepository.save(c);
	}

	@Override
	public Course updateCourse(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public void deleteCourse(Course course) {
		courseRepository.delete(course);
	}

}
