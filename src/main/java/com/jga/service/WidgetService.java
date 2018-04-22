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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jga.service.IWidgetService#addWidget(com.jga.entity.Widget)
	 */
	@Override
	public Widget addWidget(Widget wid) {
		return widgetRepository.save(wid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jga.service.IWidgetService#updateWidget(com.jga.entity.Widget)
	 */
	@Override
	public Widget updateWidget(Widget wid) {
		return widgetRepository.save(wid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jga.service.IWidgetService#deleteWidget(com.jga.entity.Widget)
	 */
	@Override
	public void deleteWidget(Widget wid) {
		widgetRepository.delete(wid);
	}
	
	@Override
	public void deleteByWidgetId(int wid) {
		widgetRepository.deleteByWidgetId(wid);
	}
}
