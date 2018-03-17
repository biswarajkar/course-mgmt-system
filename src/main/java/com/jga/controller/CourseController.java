/**
 * 
 */
package com.jga.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jga.entity.Course;
import com.jga.service.ICourseService;

/**
 * @author dey
 *
 */
@Controller
public class CourseController {
	@Autowired
	private ICourseService courseService;
	
	@GetMapping("course/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable("id") Integer id) {
		Course course = courseService.getCourseById(id);
		return new ResponseEntity<>(course, HttpStatus.OK);
	}
	
	@GetMapping("course")
	public ResponseEntity<Collection<Course>> getAllCourses() {
		Collection<Course> course = courseService.getAllCourses();
		return new ResponseEntity<>(course, HttpStatus.OK);
	}
}
