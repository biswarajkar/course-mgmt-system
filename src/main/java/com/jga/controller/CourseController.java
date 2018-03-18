/**
 * 
 */
package com.jga.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

	@GetMapping("api/course/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable("id") Integer id) {
		Course course = courseService.getCourseById(id);
		return new ResponseEntity<>(course, HttpStatus.OK);
	}

	@GetMapping("api/course")
	public ResponseEntity<Collection<Course>> getAllCourses() {
		Collection<Course> course = courseService.getAllCourses();
		return new ResponseEntity<>(course, HttpStatus.OK);
	}

	@PostMapping("course")
	public ResponseEntity<Course> addArticle(@RequestBody Course course) {
		Course newCourse = courseService.addCourse(course);
	
		return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
	}
	
	@PutMapping("course")
	public ResponseEntity<Course> updateArticle(@RequestBody Course course) {
		Course newCourse = courseService.updateCourse(course);
	
		return new ResponseEntity<>(newCourse, HttpStatus.OK);
	}
	
	
	@DeleteMapping("course")
	public ResponseEntity<Void> deleteArticle(@RequestBody Course course) {
		courseService.deleteCourse(course);
	
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
