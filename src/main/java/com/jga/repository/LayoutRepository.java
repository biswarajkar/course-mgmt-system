package com.jga.repository;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import com.jga.entity.Layout;

/**
 * @author biswaraj
 *
 */
@Transactional
public interface LayoutRepository extends CrudRepository<Layout, Integer> {
}
