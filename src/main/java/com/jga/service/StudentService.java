/**
 * 
 */
package com.jga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jga.entity.Student;
import com.jga.repository.BasePersonRepository;
import com.jga.repository.StudentRepository;

/**
 * @author dey
 *
 */
@Service
public class StudentService extends PersonService<Student> {
	@Autowired
	private StudentRepository<Student> repository;

	@Override
	protected BasePersonRepository<Student> getRepository() {
		return repository;
	}

	public Student findByUsernamePassword(String username, String password) {
		return repository.findByUsernameAndPassword(username, password);
	}

}
