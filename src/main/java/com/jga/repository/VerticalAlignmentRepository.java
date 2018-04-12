/**
 * 
 */
package com.jga.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.jga.entity.HorizontalAlignment;
import com.jga.entity.Role;
import com.jga.entity.UserAction;
import com.jga.entity.VerticalAlignment;

/**
 * @author dey
 *
 */
@Transactional
public interface VerticalAlignmentRepository extends CrudRepository<VerticalAlignment, Integer> {
}
