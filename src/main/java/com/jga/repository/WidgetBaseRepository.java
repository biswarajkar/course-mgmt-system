/**
 * 
 */
package com.jga.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.jga.entity.Widget;

/**
 * @author biswaraj
 *
 */
@NoRepositoryBean
public interface WidgetBaseRepository<T extends Widget> extends CrudRepository<Widget, Integer> {
}
