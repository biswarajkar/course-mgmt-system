/**
 * 
 */
package com.jga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jga.entity.Faculty;
import com.jga.repository.BasePersonRepository;
import com.jga.repository.FacultyRepository;

/**
 * @author dey
 *
 */
@Service
public class FacultyService extends PersonService<Faculty> {
	@Autowired
	private FacultyRepository<Faculty> repository;

	@Override
	protected BasePersonRepository<Faculty> getRepository() {
		return repository;
	}
	
	public Faculty findByUsernamePassword(String username, String password) {
		return repository.findByUsernameAndPassword(username, password);
	}
}
