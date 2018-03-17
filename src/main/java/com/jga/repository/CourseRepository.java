/**
 * 
 */
package com.jga.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jga.entity.Course;

/**
 * @author dey
 *
 */
public interface CourseRepository extends CrudRepository<Course, Integer> {
	@Query("select c from Course c where c.name = :name")
	Collection<Course> findByName(@Param("name") String name);
}
