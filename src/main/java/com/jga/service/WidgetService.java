/**
 * 
 */
package com.jga.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jga.entity.Widget;
import com.jga.repository.WidgetRepository;

/**
 * @author Biswaraj
 *
 */
@Service
public class WidgetService implements IWidgetService {

	@Autowired
	private WidgetRepository widgetRepository;

	// Might be needed for CRUD operations
	// @Autowired
	// private HtmlWidgetRepository htmlRepository;
	// @Autowired
	// private ImageWidgetRepository imageRepository;
	// @Autowired
	// private VideoWidgetRepository videoRepository;
	// @Autowired
	// private GoogleDocWidgetRepository googleDocRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jga.service.IWidgetService#getWidgetById(int)
	 */
	@Override
	public Widget getWidgetById(int id) {

		return widgetRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jga.service.IWidgetService#getAllWidgets()
	 */
	@Override
	public Collection<Widget> getAllWidgets() {
		final Collection<Widget> widgets = new ArrayList<>();
		widgetRepository.findAll().iterator().forEachRemaining(widgets::add);

		return widgets;
	}

}
