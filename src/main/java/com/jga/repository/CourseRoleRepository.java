/**
 * 
 */
package com.jga.repository;

import java.util.ArrayList;
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
<<<<<<< HEAD

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
=======
	
	@Query(value="select r.* from Role r where r.id in "
			+ "(select c.role from CourseRole c where c.courseId=:courseId and c.personId=:personId)", nativeQuery=true)
	Collection<Object> findRolesByPersonAndCourseObject(@Param("personId") int personId, @Param("courseId") int courseId);
	
	default Collection<Role> findRolesByPersonAndCourse(int personId, int courseId) {
		Collection<Role> roles = new ArrayList<>();
		for (Object cr : findRolesByPersonAndCourseObject(personId, courseId)) {
			Object[] obj = (Object[])cr;
			roles.add(new Role((Integer) obj[0], (String) obj[1]));
		}
		
		return roles;
	}
	
	
	@Query(value="select c.* from Course c where c.courseId in "
			+ "(select cr.courseId from CourseRole cr where cr.personId=:personId) GROUP BY c.courseId", nativeQuery=true)
	Collection<Course> findCoursesByPerson(@Param("personId") int personId);
	
	@Query(value="INSERT INTO CourseRole(`personId`, `courseId`, `role`) VALUES(:personId, :courseId, :roleId)", nativeQuery=true)
	@Modifying
	void insertCourseRole(@Param("personId") int personId, @Param("courseId") int courseId, @Param("roleId") int roleId);
	
	//@Modifying
	//@Query("delete from CourseRole c WHERE c.personId=:personId and c.courseId=:courseId")
	//boolean delCourseByPerson(@Param("personId") int personId, @Param("courseId") int courseId);
>>>>>>> d8a1bead9f5ac6e1c7131fed96aec184a27ca52d
}
