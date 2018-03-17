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
public interface WidgetBaseRepository<T extends Widget> extends CrudRepository<Widget, Integer>{

	  /**
	   * Method to find a Widget by Id
	   * 
	   * @param The unique Identifier of the Widget
	   * @return The Widget having the Id passed or null if no Widget is found.
	   */
	  public T findWidgetById(Integer id);
}
