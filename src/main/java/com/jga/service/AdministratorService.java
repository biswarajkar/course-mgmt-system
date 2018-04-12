/**
 * 
 */
package com.jga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jga.entity.Administrator;
import com.jga.repository.AdministratorRepository;
import com.jga.repository.BasePersonRepository;

/**
 * @author dey
 *
 */
@Service
public class AdministratorService extends PersonService<Administrator> {
	@Autowired
	private AdministratorRepository<Administrator> repository;

	@Override
	protected BasePersonRepository<Administrator> getRepository() {
		return repository;
	}
}
