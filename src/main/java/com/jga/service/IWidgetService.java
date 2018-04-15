package com.jga.service;

import java.util.Collection;

import com.jga.entity.Widget;

/**
 * @author biswaraj
 *
 */
public interface IWidgetService {

	/**
	 * Returns the Widget with the given Id
	 * 
	 * @param id
	 *            the identifier for the widget to be searched
	 * @return the Widget corresponding to the Id passed
	 */
	public Widget getWidgetById(int id);

	/**
	 * Returns all the Widgets present
	 * 
	 * @return a collection of all Widgets
	 */
	public Collection<Widget> getAllWidgets();

	/**
	 * @param wid
	 *            The Widget to be updated
	 * @return The newly created Widget
	 */
	public Widget addWidget(Widget wid);

	/**
	 * @param wid
	 *            The Widget to be updated
	 * @return The Widget instance after it has been updated
	 */
	public Widget updateWidget(Widget wid);

	/**
	 * The Widget to be deleted
	 * 
	 * @param wid
	 *            The Widget instance which was deleted
	 */
	public void deleteWidget(Widget wid);
}
