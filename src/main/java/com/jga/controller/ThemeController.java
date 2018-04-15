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
import com.jga.entity.Theme;
import com.jga.service.IThemeService;

/**
 * The Theme Controller which defines Endpoints for all Theme operations
 * 
 * @author biswaraj
 */
@Controller
public class ThemeController {

	private static final Logger logger = LogManager.getLogger(Cs5200CourseManagerApplication.class);

	@Autowired
	private IThemeService themeService;

	/**
	 * Retrieves all the Themes in the application (Administrator Only)
	 * 
	 * @return the appropriate HTTP Response as per the success of the operation
	 *         along with a collection of all Themes
	 */
	@GetMapping("api/theme")
	public ResponseEntity<Collection<Theme>> getAllThemes() {
		Collection<Theme> themes = themeService.getAllThemes();
		return new ResponseEntity<>(themes, HttpStatus.OK);
	}

	/**
	 * Retrieves the Theme with the given Identifier (Administrator Only)
	 * 
	 * @param themeId
	 *            The Theme Id of the Theme to be searched
	 * @return the appropriate HTTP Response as per the success of the operation
	 */
	@GetMapping("api/theme/{id}")
	public ResponseEntity<Theme> getThemeById(@PathVariable("id") Integer themeId) {
		Theme theme = themeService.getThemeById(themeId);
		return new ResponseEntity<>(theme, HttpStatus.OK);
	}

	/**
	 * Retrieves the Default Theme of the application
	 * 
	 * @return the appropriate HTTP Response as per the success of the operation
	 */
	@GetMapping("api/theme/0")
	public ResponseEntity<Theme> getDefaultTheme() {
		Theme theme = themeService.getThemeByName("default");
		return new ResponseEntity<>(theme, HttpStatus.OK);
	}

	/**
	 * Adds a new Theme to the application
	 * 
	 * @param theme
	 *            The Theme object to be added
	 * @return the appropriate HTTP Response as per the success of the operation
	 *         along with the newly created Theme
	 */
	@PostMapping("api/theme")
	public ResponseEntity<Theme> addTheme(@RequestBody Theme theme) {
		Theme newTheme = themeService.addTheme(theme);
		logger.warn("Theme '" + newTheme.getThemeId() + ":" + newTheme.getName() + "' added successfully");
		return new ResponseEntity<>(newTheme, HttpStatus.OK);
	}

	/**
	 * Updates an existing Theme (Administrator Only)
	 * 
	 * @param theme
	 *            The Theme object to be updated
	 * @return the appropriate HTTP Response as per the success of the operation
	 *         along with the updated Theme
	 */
	@PutMapping("api/theme")
	public ResponseEntity<Theme> updateThemeInTheme(@RequestBody Theme theme) {
		Theme updatedTheme = themeService.updateTheme(theme);
		logger.warn("Theme '" + updatedTheme.getThemeId() + ":" + updatedTheme.getName() + "' updated successfully");
		return new ResponseEntity<>(updatedTheme, HttpStatus.OK);
	}

	/**
	 * Deletes a Theme from the application (Administrator Only)
	 * 
	 * @param theme
	 *            The Theme object to be deleted
	 * @return the appropriate HTTP Response as per the success of the operation
	 */
	@DeleteMapping("api/theme")
	public ResponseEntity<Void> deleteTheme(@RequestBody Theme theme) {
		if (theme.equals(themeService.getThemeByName("default"))) {
			logger.error("Default Theme cannot be deleted");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		themeService.deleteTheme(theme);
		logger.warn("ADMIN ALERT: Theme '" + theme.getThemeId() + ":" + theme.getName() + "' deleted successfully");
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
