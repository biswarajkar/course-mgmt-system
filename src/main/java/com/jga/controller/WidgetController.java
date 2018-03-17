/**
 * 
 */
package com.jga.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jga.entity.Widget;
import com.jga.service.IWidgetService;

/**
 * @author dey
 *
 */
@Controller
public class WidgetController {
	@Autowired
	private IWidgetService widgetService;

	@GetMapping("api/widget/{id}")
	public ResponseEntity<Widget> getWidgetById(@PathVariable("id") Integer id) {
		Widget widget = widgetService.getWidgetById(id);
		return new ResponseEntity<>(widget, HttpStatus.OK);
	}

	@GetMapping("api/widget")
	public ResponseEntity<Collection<Widget>> getAllWidgets() {
		Collection<Widget> widget = widgetService.getAllWidgets();
		return new ResponseEntity<>(widget, HttpStatus.OK);
	}
}
