/**
 * 
 */
package com.jga.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jga.entity.GoogleDocWidget;
import com.jga.entity.HtmlWidget;
import com.jga.entity.ImageWidget;
import com.jga.entity.VideoWidget;
import com.jga.entity.Widget;
import com.jga.repository.GoogleDocWidgetRepository;
import com.jga.repository.HtmlWidgetRepository;
import com.jga.repository.ImageWidgetRepository;
import com.jga.repository.VideoWidgetRepository;
import com.jga.repository.WidgetRepository;

/**
 * @author Biswaraj
 *
 */
@Service
public class WidgetService implements IWidgetService {

	@Autowired
	private WidgetRepository widgetRepository;
	@Autowired
	private HtmlWidgetRepository htmlRepository;
	@Autowired
	private ImageWidgetRepository imageRepository;
	@Autowired
	private VideoWidgetRepository videoRepository;
	@Autowired
	private GoogleDocWidgetRepository googleDocRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jga.service.IWidgetService#getWidgetById(int)
	 */
	@Override
	public Widget getWidgetById(int id) {

		Widget widget = widgetRepository.findOne(id);

		try {
			// Get the Widget Type
			if (widget instanceof HtmlWidget)
				widget = htmlRepository.findOne(id);
			else if (widget instanceof ImageWidget)
				widget = imageRepository.findOne(id);
			else if (widget instanceof VideoWidget)
				widget = videoRepository.findOne(id);
			else if (widget instanceof GoogleDocWidget)
				widget = googleDocRepository.findOne(id);
			
		} catch (Exception ex) {
			System.out.println("Widget not found");
			return null;
		}

		return widget;
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
