/**
 * 
 */
package com.jga.repository;

import java.util.Collection;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jga.entity.Course;
import com.jga.entity.Role;

/**
 * @author dey
 *
 */
@Transactional
public interface CourseRoleRepository extends CrudRepository<Course, Integer> {

	@Query(value = "select r from Role r where r.id in "
			+ "(select c.roleId from CourseRole c where c.courseId=:courseId and c.personId=:personId)", nativeQuery = true)
	Collection<Role> findRolesByPersonAndCourse(@Param("personId") int personId, @Param("courseId") int courseId);

	@Query(value = "select DISTINCT(c) from Course c where c.courseId in "
			+ "(select cr.courseId from CourseRole cr where cr.personId=:personId)", nativeQuery = true)
	Collection<Course> findCoursesByPerson(@Param("personId") int personId);

	// @Modifying
	// @Query("delete from CourseRole c WHERE c.personId=:personId and
	// c.courseId=:courseId")
	// boolean delCourseByPerson(@Param("personId") int personId, @Param("courseId")
	// int courseId);
}
