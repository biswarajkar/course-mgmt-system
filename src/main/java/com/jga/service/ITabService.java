package com.jga.service;

import java.util.Collection;

import com.jga.entity.Tab;

/**
 * @author biswaraj
 *
 */
public interface ITabService {
	public Tab getTabById(int id);

	public Collection<Tab> getAllTabs();

	public Tab addTab(Tab tab);

	public Tab updateTab(Tab tab);

	public void deleteTab(Tab tab);
}
