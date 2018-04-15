package com.jga.repository;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import com.jga.entity.Page;

/**
 * @author biswaraj
 *
 */
@Transactional
public interface PageRepository extends CrudRepository<Page, Integer> {
}
