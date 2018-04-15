/**
 * 
 */
package com.jga.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.jga.entity.HorizontalAlignment;

/**
 * @author dey
 *
 */
@Transactional
public interface HorizontalAlignmentRepository extends CrudRepository<HorizontalAlignment, Integer> {
}
