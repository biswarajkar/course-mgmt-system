package com.jga.controller;

import java.util.Collection;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.jga.Cs5200CourseManagerApplication;
import com.jga.entity.Course;
import com.jga.entity.Page;
import com.jga.entity.Tab;
import com.jga.service.ICourseService;
import com.jga.service.IPageService;
import com.jga.service.ITabService;

/**
 * The Page Controller which defines Endpoints for all Page operations
 * 
 * @author biswaraj
 */
@Controller
public class PageController {

	private static final Logger logger = LogManager.getLogger(Cs5200CourseManagerApplication.class);

	@Autowired
	private ITabService tabService;
	@Autowired
	private IPageService pageService;
	@Autowired
	private ICourseService courseService;

	/**
	 * Retrieves the Page with the given Identifier
	 * 
	 * @param id
	 *            The Page Id of the Page to be searched
	 * @return the appropriate HTTP Response as per the success of the operation
	 */
	@GetMapping("api/page/{id}")
	public ResponseEntity<Page> getPageById(@PathVariable("id") Integer id) {
		Page page = pageService.getPageById(id);
		return new ResponseEntity<>(page, HttpStatus.OK);
	}

	/**
	 * Retrieves a particular Page in a Course
	 * 
	 * @param courseId
	 *            The course Id of the Course
	 * @param pageId
	 *            The Page Id of the Page to be retrieved from the Page
	 * @return the appropriate HTTP Response as per the success of the operation
	 *         along with the Page with the PageId supplied
	 */
	@GetMapping("api/course/{cid}/page/{pid}")
	public ResponseEntity<Page> getPageFromCourse(@PathVariable("cid") Integer courseId,
			@PathVariable("pid") Integer pageId) {
		try {
			Course givenCourse = courseService.getCourseById(courseId);
			Page page = pageService.getPageById(pageId);

			if (givenCourse == null || page == null) {
				logger.error("Supplied Course or Page does not exist" + "["
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			List<Page> pagesInLayout = givenCourse.getLayout().getPages();
			Integer pageIndex = pagesInLayout.indexOf(page);
			if (pageIndex < 0) {
				logger.error(
						"Supplied page '" + page.getName() + "' not found in the given course '" + givenCourse.getName()
								+ "' [" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(page, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error[" + e.getMessage() + "]: Issue in retrieving Page from Course [Course: " + courseId
					+ ", Page: " + pageId + "]\n");
			logger.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * Retrieves all the Pages in the application
	 * 
	 * @return the appropriate HTTP Response as per the success of the operation
	 *         along with a collection of all Pages
	 */
	@GetMapping("api/page")
	public ResponseEntity<Collection<Page>> getAllPages() {
		Collection<Page> pages = pageService.getAllPages();
		return new ResponseEntity<>(pages, HttpStatus.OK);
	}

	/**
	 * Retrieves all the Pages in a Course
	 * 
	 * @param courseId
	 *            The course Id of the Course
	 * @return the appropriate HTTP Response as per the success of the operation
	 *         along with a collection of all Pages in the course supplied
	 */
	@GetMapping("api/course/{cid}/page")
	public ResponseEntity<Collection<Page>> getAllPagesForCourse(@PathVariable("cid") Integer courseId) {
		try {
			Course givenCourse = courseService.getCourseById(courseId);

			if (givenCourse == null) {
				logger.error("Supplied Course does not exist" + " ["
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			List<Page> pagesInLayout = givenCourse.getLayout().getPages();
			return new ResponseEntity<>(pagesInLayout, HttpStatus.OK);

		} catch (Exception e) {
			logger.error(
					"Error[" + e.getMessage() + "]: Issue in retrieving all Pages in the Course '" + courseId + "'\n");
			logger.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("api/page/{pageId}")
	public ResponseEntity<Page> updateByPageId(@PathVariable("pageId") int pageId, @RequestParam("name") String name, 
			@RequestParam("tooltipDescription") String description) {
		pageService.updateByPageId(pageId, name, description);
		
		return getPageById(pageId);
	}
	
	
	/**
	 * Updates an existing Page inside a Course
	 * 
	 * @param courseId
	 *            The course Id of the Course
	 * @param page
	 *            The Page object to be updated
	 * @return the appropriate HTTP Response as per the success of the operation
	 *         along with the updated Page
	 */
	@PutMapping("api/course/{cid}/page")
	public ResponseEntity<Page> updatePageInCourse(@PathVariable("cid") Integer courseId, @RequestBody Page page) {
		try {
			Course givenCourse = courseService.getCourseById(courseId);

			if (givenCourse == null) {
				logger.error("Supplied Course does not exist" + " ["
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			List<Page> pagesInLayout = givenCourse.getLayout().getPages();
			Integer pageIndex = pagesInLayout.indexOf(page);
			if (pageIndex < 0) {
				logger.error(
						"Supplied page '" + page.getName() + "' not found in the given course '" + givenCourse.getName()
								+ " [" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			Page updatedPage = pageService.updatePage(page);

			logger.info("Page '" + updatedPage.getPageId() + ":" + updatedPage.getName()
					+ "' updated successfully in Course: " + givenCourse.getName());
			return new ResponseEntity<>(updatedPage, HttpStatus.OK);

		} catch (Exception e) {
			logger.error(
					"Error[" + e.getMessage() + "]: Issue in updating existing Page in Course '" + courseId + "'\n");
			logger.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * Deletes a Page from the application (Administrator Only)
	 * 
	 * @param page
	 *            The Page object to be deleted
	 * @return the appropriate HTTP Response as per the success of the operation
	 */
	@DeleteMapping("api/page")
	public ResponseEntity<Void> deletePage(@RequestBody Page page) {
		pageService.deletePage(page);
		logger.warn("ADMIN ALERT: Page '" + page.getPageId() + ":" + page.getName() + "' deleted successfully");
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("api/page/{pageId}")
	public ResponseEntity<Void> deletePage(@PathVariable("pageId") int pageId) {
		pageService.deleteByPageId(pageId);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Deletes a Page of a Course
	 * 
	 * @param courseId
	 *            The course Id of the Course
	 * @param page
	 *            The Page object to be deleted
	 * @return the appropriate HTTP Response as per the success of the operation
	 */
	@DeleteMapping("api/course/{cid}/page")
	public ResponseEntity<Void> deletePageFromCourse(@PathVariable("cid") Integer courseId, @RequestBody Page page) {

		try {
			Course givenCourse = courseService.getCourseById(courseId);
			if (givenCourse == null) {
				logger.error("Supplied Course does not exist" + " ["
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			List<Page> pagesInLayout = givenCourse.getLayout().getPages();
			Integer pageIndex = pagesInLayout.indexOf(page);
			if (pageIndex < 0) {
				logger.error(
						"Supplied page '" + page.getName() + "' not found in the given course '" + givenCourse.getName()
								+ "' [" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			logger.info(givenCourse.getLayout().getName());

			pageService.deletePage(page);

			logger.info("Page '" + page.getPageId() + ":" + page.getName() + "' deleted successfully from Course: "
					+ givenCourse.getName());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			logger.error("Error[" + e.getMessage() + "]: Issue in Deleting Page from Course '" + courseId + "'\n");
			logger.error(e.toString());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	/**
	 * Adds a new Tab to a Page
	 * 
	 * @param courseId
	 *            The course Id of the Course
	 * @param pageId
	 *            The Page Id of the Page
	 * @return the appropriate HTTP Response as per the success of the operation
	 *         along with the newly created Tab
	 */
	@PostMapping("api/course/{cid}/page/{pid}/tab")
	public ResponseEntity<Tab> addTabToPage(@PathVariable("cid") Integer courseId, @PathVariable("pid") Integer pageId,
			@RequestBody Tab tab) {
		try {
			Course givenCourse = courseService.getCourseById(courseId);
			Page givenPage = pageService.getPageById(pageId);

			if (givenCourse == null || givenPage == null) {
				logger.error("Supplied Course or Page does not exist" + "["
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			if (givenCourse.getLayout() == null) {
				logger.error("Supplied Course has no Layout/Theme assigned yet ["
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			List<Page> pagesInLayout = givenCourse.getLayout().getPages();
			Integer pageIndex = pagesInLayout.indexOf(givenPage);
			if (pageIndex < 0) {
				logger.error("Supplied page '" + givenPage.getName() + "' not found in the given course '"
						+ givenCourse.getName() + "' [" + Thread.currentThread().getStackTrace()[1].getMethodName()
						+ "]");
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			Tab newTab = tabService.addTab(tab);
			newTab.setPage(givenPage);
			givenPage.getTabs().add(newTab);
			pageService.updatePage(givenPage);
			logger.info("Tab '" + newTab.getTabId() + ":" + newTab.getName() + "' created successfully in Page '"
					+ givenPage.getName() + "'/ Course '" + givenCourse.getName() + "'");
			return new ResponseEntity<>(newTab, HttpStatus.CREATED);

		} catch (Exception e) {
			logger.error("Error[" + e.getMessage() + "]: Issue in adding Tab to Page '" + pageId + "']\n");
			logger.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
}
