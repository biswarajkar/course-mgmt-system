/**
 * 
 */
package com.jga.controller;

import java.util.Collection;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jga.Cs5200CourseManagerApplication;
import com.jga.entity.Course;
import com.jga.entity.Layout;
import com.jga.entity.Theme;
import com.jga.service.ICourseService;
import com.jga.service.ILayoutService;
import com.jga.service.IThemeService;

/**
 * @author biswaraj, dey
 *
 */
@Controller
public class CourseController {

	private static final Logger logger = LogManager.getLogger(Cs5200CourseManagerApplication.class);

	@Autowired
	private ICourseService courseService;
	@Autowired
	private ILayoutService layoutService;
	@Autowired
	private IThemeService themeService;

	@GetMapping("api/course/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable("id") Integer id) {
		Course course = courseService.getCourseById(id);
		return new ResponseEntity<>(course, HttpStatus.OK);
	}

	@GetMapping("api/course")
	public ResponseEntity<Collection<Course>> getAllCourses() {
		Collection<Course> course = courseService.getAllCourses();
		return new ResponseEntity<>(course, HttpStatus.OK);
	}

	@PostMapping("course")
	public ResponseEntity<Course> addArticle(@RequestBody Course course) {
		Course newCourse = courseService.addCourse(course);

		return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
	}

	@PutMapping("course")
	public ResponseEntity<Course> updateArticle(@RequestBody Course course) {
		Course newCourse = courseService.updateCourse(course);

		return new ResponseEntity<>(newCourse, HttpStatus.OK);
	}

	@DeleteMapping("course")
	public ResponseEntity<Void> deleteArticle(@RequestBody Course course) {
		courseService.deleteCourse(course);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Adds a new Layout to a Course
	 * 
	 * @param courseId
	 *            The course Id of the Course
	 * @param layout
	 *            The Layout object to be added
	 * @return the appropriate HTTP Response as per the success of the operation
	 *         along with the newly created Layout
	 * @author Biswaraj
	 */
	@PostMapping("api/course/{cid}/layout")
	public ResponseEntity<Layout> addLayoutToCourse(@PathVariable("cid") Integer courseId, @RequestBody Layout layout) {
		try {
			Course givenCourse = courseService.getCourseById(courseId);

			if (givenCourse == null) {
				logger.error("Supplied Course does not exist" + " ["
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			Layout newLayout = layoutService.addLayout(layout);
			newLayout.setCourse(givenCourse);
			newLayout.setUpdateDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
			givenCourse.setLayout(newLayout);
			givenCourse.setUpdateDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
			Course updatedCourse = courseService.updateCourse(givenCourse);
			Layout updatedLayout = layoutService.updateLayout(newLayout);
			logger.info("Layout '" + updatedLayout.getLayoutId() + ":" + updatedLayout.getName()
					+ "' created successfully in Course '" + givenCourse.getName() + "'");
			return new ResponseEntity<>(updatedCourse.getLayout(), HttpStatus.CREATED);

		} catch (Exception e) {
			logger.error("Error[" + e.getMessage() + "]: Issue in adding Layout to Course '" + courseId + "']\n");
			logger.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * Assigns a Theme to a Course
	 * 
	 * @param courseId
	 *            The course Id of the Course
	 * @param theme
	 *            The theme object to be assigned
	 * @return the appropriate HTTP Response as per the success of the operation
	 *         along with the newly assigned Theme
	 * @author Biswaraj
	 */
	@PostMapping("api/course/{cid}/theme")
	public ResponseEntity<Layout> assignThemeToCourse(@PathVariable("cid") Integer courseId, @RequestBody Theme theme) {
		try {
			Course givenCourse = courseService.getCourseById(courseId);

			if (givenCourse == null || theme == null || themeService.getThemeById(theme.getThemeId()) == null) {
				logger.error("Supplied Course or Theme does not exist" + " ["
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			// Create or Retrieve the Layout
			Layout layout;

			if (givenCourse.getLayout() == null) {
				layout = new Layout();
				layout.setName("default_" + givenCourse.getName().replaceAll("\\s+", ""));
				layout.setTitle("Default: " + givenCourse.getName());
				layout.setDescription("Default: " + givenCourse.getDescription());
			} else {
				layout = givenCourse.getLayout();
				layout.setUpdateDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
			}

			// Update the Layout as per the Theme
			layout.setTheme(theme);
			layout.setCustomBackground(theme.getThemeBackgroundImage());
			layout.setStylesheetLink(theme.getStylesheetLink());
			Layout updatedLayout = layoutService.updateLayout(layout);

			// Update Theme Count
			theme.setNoOfUses(theme.getNoOfUses() + 1);
			themeService.updateTheme(theme);

			// Add the created Layout to the course
			if (givenCourse.getLayout() == null)
				updatedLayout = addLayoutToCourse(givenCourse.getCourseId(), updatedLayout).getBody();

			logger.info("Layout '" + updatedLayout.getLayoutId() + ":" + updatedLayout.getName()
					+ "' successfully assigned with Theme '" + theme.getName() + "' for Course '"
					+ givenCourse.getName() + "'" + givenCourse.toString());
			return new ResponseEntity<>(updatedLayout, HttpStatus.CREATED);

		} catch (Exception e) {
			logger.error("Error[" + e.getMessage() + "]: Issue in assigning Theme to Course '" + courseId + "']\n");
			logger.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * Assigns the default Theme to a Course
	 * 
	 * @param courseId
	 *            The course Id of the Course
	 * @return the appropriate HTTP Response as per the success of the operation
	 *         along with the newly assigned Theme
	 * @author Biswaraj
	 */
	@PostMapping("api/course/{cid}/theme/0")
	public ResponseEntity<Layout> assignDefaultThemeToCourse(@PathVariable("cid") Integer courseId) {
		return assignThemeToCourse(courseId, themeService.getThemeByName("default"));
	}
}
