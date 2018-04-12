package com.jga.repository;

import javax.transaction.Transactional;

import com.jga.entity.Administrator;

/**
 * @author dey
 *
 */
@Transactional
public interface AdministratorRepository<T> extends BasePersonRepository<Administrator> {
}
