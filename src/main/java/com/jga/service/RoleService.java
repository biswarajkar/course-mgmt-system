/**
 * 
 */
package com.jga.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.jga.entity.Role;
import com.jga.repository.RoleRepository;

/**
 * @author dey
 *
 */
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;
	
	public Collection<Role> findAllRoles() {
		return (Collection<Role>) roleRepository.findAll();
	}
}
