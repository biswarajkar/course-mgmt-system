/**
 * 
 */
package com.jga.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.jga.entity.Role;

/**
 * @author dey
 *
 */
@Transactional
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
