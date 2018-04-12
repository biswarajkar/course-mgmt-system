/**
 * 
 */
package com.jga.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.jga.entity.Role;
import com.jga.entity.UserAction;

/**
 * @author dey
 *
 */
@Transactional
public interface UserActionRepository extends CrudRepository<UserAction, Integer> {
}
