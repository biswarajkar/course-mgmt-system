/*
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

import com.jga.entity.Administrator;
import com.jga.service.AdministratorService;

/**
 * @author dey
 *
 */
@Controller
public class AdminController {
	@Autowired
	private AdministratorService adminService;

	@GetMapping("api/admin/{id}")
	public ResponseEntity<Administrator> getCourseById(@PathVariable("id") Integer id) {
		Administrator admin = adminService.getById(id);
		return new ResponseEntity<>(admin, HttpStatus.OK);
	}

	@GetMapping("api/admin")
	public ResponseEntity<Collection<Administrator>> getAllCourses() {
		Collection<Administrator> admins = adminService.getAllPersons();
		return new ResponseEntity<>(admins, HttpStatus.OK);
	}

	@PostMapping("api/admin")
	public ResponseEntity<Administrator> addStudent(@RequestBody Administrator admin) {
		Administrator newAdmin = adminService.add(admin);

		return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
	}

	@PutMapping("api/admin")
	public ResponseEntity<Administrator> updateStudent(@RequestBody Administrator admin) {
		Administrator newAdmin = adminService.update(admin);

		return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
	}

	@DeleteMapping("api/admin")
	public ResponseEntity<Administrator> deleteStudent(@RequestBody Administrator admin) {
		adminService.delete(admin);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
