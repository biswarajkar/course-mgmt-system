/**
 * 
 */
package com.jga.service;

import java.util.Collection;

import com.jga.entity.Course;
import com.jga.entity.Person;

/**
 * @author dey
 *
 */

public interface IPersonService<T extends Person> {
	public T getById(int id);

	public Collection<T> getAllPersons();
	
	public T add(T person);

	public T update(T person);
	
	public void delete(T person);
}
