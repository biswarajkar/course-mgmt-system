package com.jga.repository;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import com.jga.entity.Tab;

/**
 * @author biswaraj
 *
 */
@Transactional
public interface TabRepository extends CrudRepository<Tab, Integer> {
}
