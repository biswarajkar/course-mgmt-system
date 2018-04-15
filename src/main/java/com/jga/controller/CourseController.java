/**
 * 
 */
package com.jga.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.jga.entity.Course;
import com.jga.model.CourseRole;
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
	
	@GetMapping("api/{userId}/courserole")
	public ResponseEntity<Collection<CourseRole>> getCourseRoleByUser(@PathVariable("userId") int userId) {
		Collection<CourseRole> course = courseService.getCourseRoleByPersonId(userId);
		return new ResponseEntity<>(course, HttpStatus.OK);
	}
	
	@GetMapping("api/{userId}/course")
	public ResponseEntity<Collection<Course>> getCourseByUser(@PathVariable("userId") int userId) {
		Collection<CourseRole> courseRole = courseService.getCourseRoleByPersonId(userId);
		Collection<Course> courses = new ArrayList<>();
		if (courseRole != null) {
			for (CourseRole cr : courseRole) {
				courses.add(cr.getCourse());
			}
		}
		
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}
	

	@PostMapping("api/course")

	public ResponseEntity<Course> addCourse(@RequestBody Course course, @RequestParam("personId") int personId, @RequestParam("roleType") String role) {
		Course newCourse = courseService.addCourse(course, personId, role);
		
		return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
	}
	
	@PutMapping("api/course")
	public ResponseEntity<Course> updateCourse(@RequestBody Course course) {
		Course newCourse = courseService.updateCourse(course);
	
		return new ResponseEntity<>(newCourse, HttpStatus.OK);
	}
	
	
	@DeleteMapping("api/course")
	public ResponseEntity<Void> deleteArticle(@RequestBody Course course) {
		courseService.deleteCourse(course);
	
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
