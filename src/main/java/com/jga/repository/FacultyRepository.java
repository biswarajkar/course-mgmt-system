package com.jga.repository;

import javax.transaction.Transactional;

import com.jga.entity.Faculty;

/**
 * @author dey
 *
 */
@Transactional
public interface FacultyRepository<T> extends BasePersonRepository<Faculty> {
}
