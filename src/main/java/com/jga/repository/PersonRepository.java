package com.jga.repository;

import javax.transaction.Transactional;

import com.jga.entity.Person;

/**
 * @author dey
 *
 */
@Transactional
public interface PersonRepository extends BasePersonRepository<Person> {
}
