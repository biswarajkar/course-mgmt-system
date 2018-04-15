package com.jga.service;

import java.util.Collection;

import com.jga.entity.Theme;

/**
 * @author biswaraj
 *
 */
public interface IThemeService {
	public Theme getThemeById(Integer id);

	public Theme getThemeByName(String name);

	public Collection<Theme> getAllThemes();

	public Theme addTheme(Theme theme);

	public Theme updateTheme(Theme theme);

	public void deleteTheme(Theme theme);
}
