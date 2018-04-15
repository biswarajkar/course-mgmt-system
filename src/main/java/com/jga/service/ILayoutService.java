package com.jga.service;

import java.util.Collection;

import com.jga.entity.Layout;

/**
 * @author biswaraj
 *
 */
public interface ILayoutService {
	public Layout getLayoutById(int id);

	public Collection<Layout> getAllLayouts();

	public Layout addLayout(Layout layout);

	public Layout updateLayout(Layout layout);

	public void deleteLayout(Layout layout);
}
