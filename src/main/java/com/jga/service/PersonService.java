/**
 * 
 */
package com.jga.service;

import java.util.ArrayList;
import java.util.Collection;

import com.jga.entity.Person;
import com.jga.repository.BasePersonRepository;

/**
 * @author dey
 *
 */
public abstract class PersonService<T extends Person> implements IPersonService<T> {

	protected abstract BasePersonRepository<T> getRepository();

	@Override
	public T getById(int id) {
		return getRepository().findOne(id);
	}

	@Override
	public Collection<T> getAllPersons() {
		final Collection<T> persons = new ArrayList<>();
		getRepository().findAll().iterator().forEachRemaining(persons::add);

		return persons;

	}

	@Override
	public T add(T person) {
		return getRepository().save(person);
	}

	@Override
	public T update(T person) {
		return getRepository().save(person);
	}

	@Override
	public void delete(T person) {
		getRepository().delete(person);
	}

}
