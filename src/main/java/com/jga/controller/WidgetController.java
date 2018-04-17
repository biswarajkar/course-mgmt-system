/**
 * 
 */
package com.jga.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jga.Cs5200CourseManagerApplication;
import com.jga.entity.Course;
import com.jga.entity.Page;
import com.jga.entity.Tab;
import com.jga.entity.Widget;
import com.jga.service.ICourseService;
import com.jga.service.IPageService;
import com.jga.service.ITabService;
import com.jga.service.IWidgetService;

/**
 * The Widget Controller which defines Endpoints for all Widget operations
 * 
 * @author biswaraj
 */
@Controller
public class WidgetController {
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
	 * Retrieves the Widget with the given Identifier
	 * 
	 * @param id
	 *            The Widget Id of the Widget to be searched
	 * @return the appropriate HTTP Response as per the success of the operation
	 */
	@GetMapping("api/widget/{id}")
	public ResponseEntity<Widget> getWidgetById(@PathVariable("id") Integer id) {
		Widget widget = widgetService.getWidgetById(id);
		return new ResponseEntity<>(widget, HttpStatus.OK);
	}
	
	@GetMapping("api/page/{pid}/widget")
	public ResponseEntity<Collection<Widget>> getWidgetByPageId(
			@PathVariable("pid") int pageId) {
			Page givenPage = pageService.getPageById(pageId);
			
			List<Widget> widgets = new ArrayList<>();
			
			for (Tab tab : givenPage.getTabs()) {
				widgets.addAll(tab.getWidgets());
			}
				
			return new ResponseEntity<>(widgets, HttpStatus.OK);
	}

	/**
	 * Retrieves a particular Widget in a given Tab of a Page in a Course
	 * 
	 * @param courseId
	 *            The course Id of the Course
	 * @param pageId
	 *            The Page Id of the Page inside the Course
	 * @param tabId
	 *            The Tab Id of the Tab inside the Page
	 * @param widgetId
	 *            The Widget Id of the Widget to be retrieved from the Tab
	 * @return the appropriate HTTP Response as per the success of the operation
	 *         along with the Widget in the Tab/Page of the WidgetId supplied
	 */
	@GetMapping("api/course/{cid}/page/{pid}/tab/{tid}/widget/{wid}")
	public ResponseEntity<Widget> getWidgetFromTab(@PathVariable("cid") Integer courseId,
			@PathVariable("pid") Integer pageId, @PathVariable("tid") Integer tabId,
			@PathVariable("wid") Integer widgetId) {
		try {
			Course givenCourse = courseService.getCourseById(courseId);
			Page givenPage = pageService.getPageById(pageId);
			Tab givenTab = tabService.getTabById(tabId);
			Widget widget = widgetService.getWidgetById(widgetId);

			if (givenCourse == null || givenPage == null || givenTab == null || widget == null) {
				logger.error("Supplied Course or Page or Tab or Widget does not exist ["
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
			Integer tabIndex = tabsInPage.indexOf(givenTab);
			if (tabIndex < 0) {
				logger.error("Supplied tab '" + givenTab.getName() + "' not found in the given course '"
						+ givenCourse.getName() + " and page '" + givenPage.getName() + "' ["
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			List<Widget> widgetsInTab = new ArrayList<Widget>(tabsInPage.get(tabIndex).getWidgets());
			Integer widgetIndex = widgetsInTab.indexOf(widget);
			if (widgetIndex < 0) {
				logger.error("Supplied widget '" + widget.getName() + "' not found in the tab '" + givenTab.getName()
						+ "' of the given course '" + givenCourse.getName() + " and page '" + givenPage.getName()
						+ "' [" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(widget, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error[" + e.getMessage() + "]: Issue in retrieving Widget from Tab [Course: " + courseId
					+ ", Page: " + pageId + ", Tab: " + tabId + ", Widget: " + widgetId + "]\n");
			logger.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * Retrieves all the Widgets in the application
	 * 
	 * @return the appropriate HTTP Response as per the success of the operation
	 *         along with a collection of all Widgets
	 */
	@GetMapping("api/widget")
	public ResponseEntity<Collection<Widget>> getAllWidgets() {
		Collection<Widget> widgets = widgetService.getAllWidgets();
		return new ResponseEntity<>(widgets, HttpStatus.OK);
	}

	/**
	 * Retrieves all the Widgets for a given Tab in a Page of a Course
	 * 
	 * @param courseId
	 *            The course Id of the Course
	 * @param pageId
	 *            The Page Id of the Page inside the Course
	 * @param tabId
	 *            The Tab Id of the Tab inside the Page
	 * @return the appropriate HTTP Response as per the success of the operation
	 *         along with a collection of all Widgets in the Tab/Page of the course
	 *         supplied
	 */
	@GetMapping("api/course/{cid}/page/{pid}/tab/{tid}/widget")
	public ResponseEntity<Collection<Widget>> getAllWidgetsForTab(@PathVariable("cid") Integer courseId,
			@PathVariable("pid") Integer pageId, @PathVariable("tid") Integer tabId) {

		try {
			Course givenCourse = courseService.getCourseById(courseId);
			Page givenPage = pageService.getPageById(pageId);
			Tab givenTab = tabService.getTabById(tabId);

			if (givenCourse == null || givenPage == null || givenTab == null) {
				logger.error("Supplied Course or Page or Tab does not exist ["
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
			Integer tabIndex = tabsInPage.indexOf(givenTab);
			if (tabIndex < 0) {
				logger.error("Supplied tab '" + givenTab.getName() + "' not found in the given course '"
						+ givenCourse.getName() + "' [" + Thread.currentThread().getStackTrace()[1].getMethodName()
						+ "]");
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			Collection<Widget> widgetsInTab = tabsInPage.get(tabIndex).getWidgets();
			return new ResponseEntity<>(widgetsInTab, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error[" + e.getMessage() + "]: Issue in retrieving all Widgets in Tab [Course: " + courseId
					+ ", Page: " + pageId + ", Tab: " + tabId + "]\n");
			logger.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}

	}

	/**
	 * Updates an existing Widget inside of a given Tab in a Page of a Course
	 * 
	 * @param courseId
	 *            The course Id of the Course
	 * @param pageId
	 *            The Page Id of the Page inside the Course
	 * @param tabId
	 *            The Tab Id of the Tab inside the Page
	 * @param widget
	 *            The Widget object to be updated
	 * @return the appropriate HTTP Response as per the success of the operation
	 *         along with the updated Widget
	 */
	@PutMapping("api/course/{cid}/page/{pid}/tab/{tid}/widget")
	public ResponseEntity<Widget> updateWidgetInTab(@PathVariable("cid") Integer courseId,
			@PathVariable("pid") Integer pageId, @PathVariable("tid") Integer tabId, @RequestBody Widget widget) {
		try {
			Course givenCourse = courseService.getCourseById(courseId);
			Page givenPage = pageService.getPageById(pageId);
			Tab givenTab = tabService.getTabById(tabId);

			if (givenCourse == null || givenPage == null || givenTab == null) {
				logger.error("Supplied Course or Page or Tab does not exist ["
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
			Integer tabIndex = tabsInPage.indexOf(givenTab);
			if (tabIndex < 0) {
				logger.error("Supplied tab '" + givenTab.getName() + "' not found in the given course '"
						+ givenCourse.getName() + " and page '" + givenPage.getName() + "' ["
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			List<Widget> widgetsInTab = new ArrayList<Widget>(tabsInPage.get(tabIndex).getWidgets());
			Integer widgetIndex = widgetsInTab.indexOf(widget);
			if (widgetIndex < 0) {
				logger.error("Supplied widget '" + widget.getName() + "' not found in the tab '" + givenTab.getName()
						+ "' of the given course '" + givenCourse.getName() + " and page '" + givenPage.getName()
						+ "' [" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			Widget updatedWidget = widgetService.updateWidget(widget);
			logger.info("Widget '" + widget.getWidgetId() + ":" + widget.getName() + "' updated successfully in Tab: "
					+ givenTab.getName());
			return new ResponseEntity<>(updatedWidget, HttpStatus.CREATED);

		} catch (Exception e) {
			logger.error("Error[" + e.getMessage() + "]: Issue in updating existing Widget in Tab [Course: " + courseId
					+ ", Page: " + pageId + ", Tab: " + tabId + "]\n");
			logger.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * Deletes a Widget from the application (Administrator Only)
	 * 
	 * @param widget
	 *            The Widget object to be deleted
	 * @return the appropriate HTTP Response as per the success of the operation
	 */
	@DeleteMapping("api/widget")
	public ResponseEntity<Void> deleteWidget(@RequestBody Widget widget) {
		widgetService.deleteWidget(widget);
		logger.warn("ADMIN ALERT: Widget '" + widget.getWidgetId() + ":" + widget.getName() + "' deleted successfully");
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Deletes a Widget from a given Tab inside a Page of a Course
	 * 
	 * @param courseId
	 *            The course Id of the Course
	 * @param pageId
	 *            The Page Id of the Page inside the Course
	 * @param tabId
	 *            The Tab Id of the Tab inside the Page
	 * @param widget
	 *            The Widget object to be deleted
	 * @return the appropriate HTTP Response as per the success of the operation
	 */
	@DeleteMapping("api/course/{cid}/page/{pid}/tab/{tid}/widget")
	public ResponseEntity<Void> deleteWidgetFromTab(@PathVariable("cid") Integer courseId,
			@PathVariable("pid") Integer pageId, @PathVariable("tid") Integer tabId, @RequestBody Widget widget) {
		try {
			Course givenCourse = courseService.getCourseById(courseId);
			Page givenPage = pageService.getPageById(pageId);
			Tab givenTab = tabService.getTabById(tabId);

			if (givenCourse == null || givenPage == null || givenTab == null) {
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
			Integer tabIndex = tabsInPage.indexOf(givenTab);
			if (tabIndex < 0) {
				logger.error("Supplied tab '" + givenTab.getName() + "' not found in the given course '"
						+ givenCourse.getName() + " and page '" + givenPage.getName() + "' ["
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			List<Widget> widgetsInTab = new ArrayList<Widget>(tabsInPage.get(tabIndex).getWidgets());
			Integer widgetIndex = widgetsInTab.indexOf(widget);
			if (widgetIndex < 0) {
				logger.error("Supplied widget '" + widget.getName() + "' not found in the tab '" + givenTab.getName()
						+ "' of the given course '" + givenCourse.getName() + " and page '" + givenPage.getName()
						+ "' [" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			widgetService.deleteWidget(widget);
			logger.info("Widget '" + widget.getWidgetId() + ":" + widget.getName() + "' deleted successfully from Tab: "
					+ givenTab.getName() + "'");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			logger.error("Error[" + e.getMessage() + "]: Issue in Deleting Widget in Tab [Course: " + courseId
					+ ", Page: " + pageId + ", Tab: " + tabId + "]\n");
			logger.error(e.toString());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
