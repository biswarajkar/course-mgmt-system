/**
 * 
 */
package com.jga.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

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
import com.jga.entity.GoogleDocWidget;
import com.jga.entity.HtmlWidget;
import com.jga.entity.ImageWidget;
import com.jga.entity.Page;
import com.jga.entity.Tab;
import com.jga.entity.VideoWidget;
import com.jga.entity.Widget;
import com.jga.service.ICourseService;
import com.jga.service.IPageService;
import com.jga.service.ITabService;
import com.jga.service.IWidgetService;

/**
 * The Tab Controller which defines Endpoints for all Tab operations
 * 
 * @author biswaraj
 */
@Controller
public class TabController {

	private static final Logger logger = LogManager.getLogger(Cs5200CourseManagerApplication.class);

	@Autowired
	private IWidgetService widgetService;
	@Autowired
	private ITabService tabService;
	@Autowired
	private IPageService pageService;
	@Autowired
	private ICourseService courseService;

	/**
	 * Retrieves the Tab with the given Identifier
	 * 
	 * @param id
	 *            The Tab Id of the Tab to be searched
	 * @return the appropriate HTTP Response as per the success of the operation
	 */
	@GetMapping("api/tab/{id}")
	public ResponseEntity<Tab> getTabById(@PathVariable("id") Integer id) {
		Tab tab = tabService.getTabById(id);
		return new ResponseEntity<>(tab, HttpStatus.OK);
	}

	/**
	 * Retrieves a particular Tab in a Page of a Course
	 * 
	 * @param courseId
	 *            The course Id of the Course
	 * @param pageId
	 *            The Page Id of the Page inside the Course
	 * @param tabId
	 *            The Tab Id of the Tab to be retrieved from the Page
	 * @return the appropriate HTTP Response as per the success of the operation
	 *         along with the Tab in the Tab/Page of the TabId supplied
	 */
	@GetMapping("api/course/{cid}/page/{pid}/tab/{tid}")
	public ResponseEntity<Tab> getTabFromPage(@PathVariable("cid") Integer courseId,
			@PathVariable("pid") Integer pageId, @PathVariable("tid") Integer tabId) {
		try {
			Course givenCourse = courseService.getCourseById(courseId);
			Page givenPage = pageService.getPageById(pageId);
			Tab tab = tabService.getTabById(tabId);

			if (givenCourse == null || givenPage == null || tab == null) {
				logger.error("Supplied Course or Page or Tab does not exist" + " ["
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			List<Page> pagesInLayout = givenCourse.getLayout().getPages();
			Integer pageIndex = pagesInLayout.indexOf(givenPage);
			if (pageIndex < 0) {
				logger.error("Supplied page '" + givenPage.getName() + "' not found in the given course '"
						+ givenCourse.getName() + "'" + " [" + Thread.currentThread().getStackTrace()[1].getMethodName()
						+ "]");
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			List<Tab> tabsInPage = pagesInLayout.get(pageIndex).getTabs();
			Integer tabIndex = tabsInPage.indexOf(tab);
			if (tabIndex < 0) {
				logger.error("Supplied tab '" + tab.getName() + "' not found in the given course '"
						+ givenCourse.getName() + " and page '" + givenPage.getName() + "' ["
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tab, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error[" + e.getMessage() + "]: Issue in retrieving Tab from Page [Course: " + courseId
					+ ", Page: " + pageId + ", Tab: " + tabId + "]\n");
			logger.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * Retrieves all the Tabs in the application
	 * 
	 * @return the appropriate HTTP Response as per the success of the operation
	 *         along with a collection of all Tabs
	 */
	@GetMapping("api/tab")
	public ResponseEntity<Collection<Tab>> getAllTabs() {
		Collection<Tab> tabs = tabService.getAllTabs();
		return new ResponseEntity<>(tabs, HttpStatus.OK);
	}

	/**
	 * Retrieves all the Tabs in a Page of a Course
	 * 
	 * @param courseId
	 *            The course Id of the Course
	 * @param pageId
	 *            The Page Id of the Page inside the Course
	 * @return the appropriate HTTP Response as per the success of the operation
	 *         along with a collection of all Tabs in the Tab/Page of the course
	 *         supplied
	 */
	@GetMapping("api/course/{cid}/page/{pid}/tab")
	public ResponseEntity<Collection<Tab>> getAllTabsForPage(@PathVariable("cid") Integer courseId,
			@PathVariable("pid") Integer pageId) {
		try {
			Course givenCourse = courseService.getCourseById(courseId);
			Page givenPage = pageService.getPageById(pageId);

			if (givenCourse == null || givenPage == null) {
				logger.error("Supplied Course or Page does not exist" + " ["
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

			List<Tab> tabsInPage = pagesInLayout.get(pageIndex).getTabs();
			return new ResponseEntity<>(tabsInPage, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error[" + e.getMessage() + "]: Issue in retrieving all Tabs in a Page [Course: " + courseId
					+ ", Page: " + pageId + "]\n");
			logger.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * Updates an existing Tab inside a Page of a Course
	 * 
	 * @param courseId
	 *            The course Id of the Course
	 * @param pageId
	 *            The Page Id of the Page inside the Course
	 * @param tab
	 *            The Tab object to be updated
	 * @return the appropriate HTTP Response as per the success of the operation
	 *         along with the updated Tab
	 */
	@PutMapping("api/course/{cid}/page/{pid}/tab")
	public ResponseEntity<Tab> updateTabInPage(@PathVariable("cid") Integer courseId,
			@PathVariable("pid") Integer pageId, @RequestBody Tab tab) {
		try {
			Course givenCourse = courseService.getCourseById(courseId);
			Page givenPage = pageService.getPageById(pageId);

			if (givenCourse == null || givenPage == null) {
				logger.error("Supplied Course or Page does not exist" + " ["
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

			List<Tab> tabsInPage = pagesInLayout.get(pageIndex).getTabs();
			Integer tabIndex = tabsInPage.indexOf(tab);
			if (tabIndex < 0) {
				logger.error("Supplied tab '" + tab.getName() + "' not found in the given course '"
						+ givenCourse.getName() + " and page '" + givenPage.getName() + "' ["
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			Tab updatedTab = tabService.updateTab(tab);
			logger.info("Tab '" + updatedTab.getTabId() + ":" + updatedTab.getName()
					+ "' updated successfully in Page: " + givenPage.getName());
			return new ResponseEntity<>(updatedTab, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error[" + e.getMessage() + "]: Issue in updating existing Tab in Page [Course: " + courseId
					+ ", Page: " + pageId + "]\n");
			logger.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * Deletes a Tab from the application (Administrator Only)
	 * 
	 * @param tab
	 *            The Tab object to be deleted
	 * @return the appropriate HTTP Response as per the success of the operation
	 */
	@DeleteMapping("api/tab")
	public ResponseEntity<Void> deleteTab(@RequestBody Tab tab) {
		tabService.deleteTab(tab);
		logger.warn("ADMIN ALERT: Tab '" + tab.getTabId() + ":" + tab.getName() + "' deleted successfully");
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Deletes a Tab inside a Page of a Course
	 * 
	 * @param courseId
	 *            The course Id of the Course
	 * @param pageId
	 *            The Page Id of the Page inside the Course
	 * @param tab
	 *            The Tab object to be deleted
	 * @return the appropriate HTTP Response as per the success of the operation
	 */
	@DeleteMapping("api/course/{cid}/page/{pid}/tab")
	public ResponseEntity<Void> deleteTabFromPage(@PathVariable("cid") Integer courseId,
			@PathVariable("pid") Integer pageId, @RequestBody Tab tab) {

		try {
			Course givenCourse = courseService.getCourseById(courseId);
			Page givenPage = pageService.getPageById(pageId);

			if (givenCourse == null || givenPage == null || tab == null) {
				logger.error("Supplied Course or Page or Tab does not exist ["
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			List<Page> pagesInLayout = givenCourse.getLayout().getPages();
			Integer pageIndex = pagesInLayout.indexOf(givenPage);
			if (pageIndex < 0) {
				logger.error("Supplied page '" + givenPage.getName() + "' not found in the given course '"
						+ givenCourse.getName() + "' [" + Thread.currentThread().getStackTrace()[1].getMethodName()
						+ "]");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			List<Tab> tabsInPage = pagesInLayout.get(pageIndex).getTabs();
			Integer tabIndex = tabsInPage.indexOf(tab);
			if (tabIndex < 0) {
				logger.error("Supplied tab '" + tab.getName() + "' not found in the given course '"
						+ givenCourse.getName() + " and page '" + givenPage.getName() + "' ["
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			tabService.deleteTab(tab);
			logger.info("Tab '" + tab.getTabId() + ":" + tab.getName() + "' deleted successfully from Page: "
					+ givenPage.getName());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			logger.error("Error[" + e.getMessage() + "]: Issue in Deleting Widget in Tab [Course: " + courseId
					+ ", Page: " + pageId + "]\n");
			logger.error(e.toString());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	/**
	 * Adds a new Widget to a given Tab in a Page of a Course
	 * 
	 * @param courseId
	 *            The course Id of the Course
	 * @param pageId
	 *            The Page Id of the Page inside the Course
	 * @param tabId
	 *            The Tab Id of the Tab inside the Page
	 * @param widget
	 *            The Widget object to be added
	 * @return the appropriate HTTP Response as per the success of the operation
	 *         along with the newly created Widget
	 */
	@PostMapping("api/course/{cid}/page/{pid}/tab/{tid}/widget")
	public ResponseEntity<Widget> addWidgetToTab(@PathVariable("cid") Integer courseId,
			@PathVariable("pid") Integer pageId, @PathVariable("tid") Integer tabId, @RequestBody Widget wid) {
		try {
			Course givenCourse = courseService.getCourseById(courseId);
			Page givenPage = pageService.getPageById(pageId);
			Tab giventab = tabService.getTabById(tabId);

			if (givenCourse == null || givenPage == null || giventab == null) {
				logger.error("Supplied Course or Page or Tab does not exist" + " ["
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			List<Page> pagesInLayout = givenCourse.getLayout().getPages();
			Integer pageIndex = pagesInLayout.indexOf(givenPage);
			if (pageIndex < 0) {
				logger.error("Supplied page '" + givenPage.getName() + "' not found in the given course '"
						+ givenCourse.getName() + "'" + " [" + Thread.currentThread().getStackTrace()[1].getMethodName()
						+ "]");
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			List<Tab> tabsInPage = pagesInLayout.get(pageIndex).getTabs();
			Integer tabIndex = tabsInPage.indexOf(giventab);
			if (tabIndex < 0) {
				logger.error("Supplied tab '" + giventab.getName() + "' not found in the given course '"
						+ givenCourse.getName() + " and page '" + givenPage.getName() + "' ["
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			Widget newWidget = widgetService.addWidget(wid);
			giventab.getWidgets().add(newWidget);
			tabService.updateTab(giventab);
			logger.info("Widget '" + newWidget.getWidgetId() + ":" + newWidget.getName()
					+ "' created successfully in Tab '" + giventab.getName() + "'/Page '" + givenPage.getName()
					+ "'/Course '" + givenCourse.getName() + "'");
			return new ResponseEntity<>(newWidget, HttpStatus.CREATED);

		} catch (Exception e) {
			logger.error("Error[" + e.getMessage() + "]: Issue in adding Widget to Tab '" + tabId + "']\n");
			logger.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("api/page/{pid}/imagewidget")
	@Transactional
	public ResponseEntity<Widget> addImageWidgetToTab(@PathVariable("pid") int pid,
			@RequestBody ImageWidget widget) {
		Page page = pageService.getPageById(pid);
		
		Tab tab = new Tab();
		tab.setHorizontalOrder(0);
		page.addTab(tab);
		tab.setPage(page);
		tab.setWidgets(Arrays.asList(widget));
		
		widget.setTabs(Arrays.asList(tab));
		return new ResponseEntity<>(widgetService.addWidget(widget), HttpStatus.CREATED);
	}
	
	@PostMapping("api/page/{pid}/videowidget")
	@Transactional
	public ResponseEntity<Widget> addVideoWidgetToTab(@PathVariable("pid") int pid,
			@RequestBody VideoWidget widget) {
		Page page = pageService.getPageById(pid);
		
		Tab tab = new Tab();
		tab.setHorizontalOrder(0);
		page.addTab(tab);
		tab.setPage(page);
		tab.setWidgets(Arrays.asList(widget));
		
		widget.setTabs(Arrays.asList(tab));
		return new ResponseEntity<>(widgetService.addWidget(widget), HttpStatus.CREATED);
	}
	
	
	@PostMapping("api/page/{pid}/htmlwidget")
	@Transactional
	public ResponseEntity<Widget> addHTMLWidgetToTab(@PathVariable("pid") int pid,
			@RequestBody HtmlWidget widget) {
		Page page = pageService.getPageById(pid);
		
		Tab tab = new Tab();
		tab.setHorizontalOrder(0);
		page.addTab(tab);
		tab.setPage(page);
		tab.setWidgets(Arrays.asList(widget));
		
		widget.setTabs(Arrays.asList(tab));
		return new ResponseEntity<>(widgetService.addWidget(widget), HttpStatus.CREATED);
	}
	
	@PostMapping("api/page/{pid}/googledocwidget")
	@Transactional
	public ResponseEntity<Widget> addHTMLWidgetToTab(@PathVariable("pid") int pid,
			@RequestBody GoogleDocWidget widget) {
		Page page = pageService.getPageById(pid);
		
		Tab tab = new Tab();
		tab.setHorizontalOrder(0);
		page.addTab(tab);
		tab.setPage(page);
		tab.setWidgets(Arrays.asList(widget));
		
		widget.setTabs(Arrays.asList(tab));
		return new ResponseEntity<>(widgetService.addWidget(widget), HttpStatus.CREATED);
	}
	
	
}
